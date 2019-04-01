//package com.csvConvert.company.extractcardrenewalcounter;
//
//import com.worldline.sdco.easyflow.imex.AbstractCollectionItemProcessor;
//import com.worldline.sdco.easyflow.imex.JobContext;
//import net.atos.wlp.cms.batches.extractcardrenewalcounter.enums.PeriodicityEnum;
//import net.atos.wlp.cms.batches.extractcardrenewalcounter.xml.*;
//import net.atos.wlp.cms.core.RenewalCounterDTO;
//import net.atos.wlp.cms.core.cardprofilemanagement.Artwork;
//import net.atos.wlp.cms.core.cardprofilemanagement.ArtworkQueries;
//import net.atos.wlp.cms.core.cardprofilemanagement.CardStyle;
//import net.atos.wlp.cms.core.cardprofilemanagement.cardmodels.CardModelQueries;
//import net.atos.wlp.cms.core.cardprofilemanagement.cardmodels.technologyapplication.TechnologyAndApplicationModel;
//import net.atos.wlp.cms.core.cardprofilemanagement.cardtemplate.CardTemplate;
//import net.atos.wlp.cms.core.cardprofilemanagement.cardtemplate.CardTemplateQueries;
//import net.atos.wlp.cms.core.utils.DateUtils;
//import net.atos.wlp.commoncomponent.commonobjects.SmallObjectsException;
//import net.atos.wlp.commoncomponent.commonobjects.issuerinternal.Issuer;
//import net.atos.wlp.commoncomponent.commonobjects.issuerinternal.IssuerInternalManager;
//import net.atos.wlp.commoncomponent.simpleobjects.exception.ConstraintViolatedException;
//import net.atos.xa.resourcelocator.ResourceLocator;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * Special implementation for processing a collection
// */
//public class RenewalCounterProcessorImpl extends AbstractCollectionItemProcessor<RenewalCounterDTO> {
//
//    private IssuerInternalManager issuerInternalManager = ResourceLocator.lookup(IssuerInternalManager.class);
//
//    private CardTemplateQueries cardTemplateQueries = ResourceLocator.lookup(CardTemplateQueries.class);
//
//    private CardModelQueries cardModelQueries = ResourceLocator.lookup(CardModelQueries.class);
//
//    private ArtworkQueries artworkQueries = ResourceLocator.lookup(ArtworkQueries.class);
//
//    @Override
//    public void processOneItem(RenewalCounterDTO dtoItem, JobContext context, boolean isRetry) throws Exception {
//
//        RenewalCounter renewalCounter = context.getVariable(RenewalCounterConstants.RENEWAL_COUNTER, RenewalCounter.class);
//
//        IssuerMC currentIssuerMC = processIssuerMCData(dtoItem, renewalCounter);
//
//        CardProducerMC currentCardProducerMC = processCardProducerMCData(dtoItem, currentIssuerMC);
//
//        CardTemplateMC currentCardTemplateMC = processCardTemplateMCData(dtoItem, currentCardProducerMC, currentIssuerMC);
//
//        CardStyleMC currentCardStyle = processCardStyleMCData(dtoItem, currentCardTemplateMC);
//
//        ArtworkMC artworkMC = processArtworkMCData(dtoItem, currentCardStyle, renewalCounter);
//
//        Long countForPeriod = artworkMC.getPeriodicCounters().getPeriodsMap().get(dtoItem.getPeriod());
//        // Process only the PeriodicCounters within expected periodicity
//        if (countForPeriod != null) {
//            artworkMC.getPeriodicCounters().getPeriodsMap().put(dtoItem.getPeriod(), countForPeriod + dtoItem.getCount());
//        }
//    }
//
//    private IssuerMC processIssuerMCData(RenewalCounterDTO dtoItem, RenewalCounter renewalCounter) throws ConstraintViolatedException, SmallObjectsException {
//        Long dtoIssuerId = dtoItem.getIssuerId();
//        IssuerMC currentIssuerMC = null;
//        if (renewalCounter.getIssuers() == null) {
//            renewalCounter.setIssuers(new ArrayList<IssuerMC>());
//        }
//        for (IssuerMC issuerMC : renewalCounter.getIssuers()) {
//            if (Objects.equals(issuerMC.getIssuerId(), dtoIssuerId)) {
//                currentIssuerMC = issuerMC;
//                break;
//            }
//        }
//        if (currentIssuerMC == null) {
//            currentIssuerMC = createIssuerMC(dtoIssuerId);
//            renewalCounter.getIssuers().add(currentIssuerMC);
//        }
//        return currentIssuerMC;
//    }
//
//    private IssuerMC createIssuerMC(long issuerId) throws ConstraintViolatedException, SmallObjectsException {
//        Issuer issuer = issuerInternalManager.getIssuerById(issuerId);
//        Objects.requireNonNull(issuer, String.format("No Issuer was found with id: %d.", issuerId));
//        IssuerMC issuerMC = new IssuerMC();
//        issuerMC.setIssuerId(issuerId);
//        issuerMC.setIssuerName(issuer.getName());
//        if (issuer.getParentIssuer() != null) {
//            issuerMC.setParentIssuerId(issuer.getParentIssuer().getId());
//        }
//        return issuerMC;
//    }
//
//    private CardProducerMC processCardProducerMCData(RenewalCounterDTO dtoItem, IssuerMC currentIssuerMC) {
//        String dtoCardProducerCode = dtoItem.getCardProducerCode();
//        CardProducerMC currentCardProducerMC = null;
//        if (currentIssuerMC.getCardProducers() == null) {
//            currentIssuerMC.setCardProducers(new ArrayList<CardProducerMC>());
//        }
//        for (CardProducerMC cardProducerMC : currentIssuerMC.getCardProducers()) {
//            if (Objects.equals(cardProducerMC.getCardProducerCode(), dtoCardProducerCode)) {
//                currentCardProducerMC = cardProducerMC;
//                break;
//            }
//        }
//        if (currentCardProducerMC == null) {
//            currentCardProducerMC = createCardProducerMC(dtoCardProducerCode);
//            currentIssuerMC.getCardProducers().add(currentCardProducerMC);
//        }
//        return currentCardProducerMC;
//    }
//
//    private CardProducerMC createCardProducerMC(String cardProducerCode) {
//        CardProducerMC cardProducerMC = new CardProducerMC();
//        cardProducerMC.setCardProducerCode(cardProducerCode);
//        return cardProducerMC;
//    }
//
//    private CardTemplateMC processCardTemplateMCData(RenewalCounterDTO dtoItem, CardProducerMC currentCardProducerMC, IssuerMC currentIssuerMC) {
//        CardTemplateMC currentCardTemplateMC = null;
//        String dtoTemplateReference = dtoItem.getTemplateReference();
//        if (currentCardProducerMC.getCardTemplates() == null) {
//            currentCardProducerMC.setCardTemplates(new ArrayList<CardTemplateMC>());
//        }
//        for (CardTemplateMC cardTemplateMC : currentCardProducerMC.getCardTemplates()) {
//            if (Objects.equals(cardTemplateMC.getTemplateReference(), dtoTemplateReference)) {
//                currentCardTemplateMC = cardTemplateMC;
//                break;
//            }
//        }
//        if (currentCardTemplateMC == null) {
//            currentCardTemplateMC = createCardTemplateMC(dtoTemplateReference, currentIssuerMC.getIssuerId());
//            currentCardProducerMC.getCardTemplates().add(currentCardTemplateMC);
//        }
//        return currentCardTemplateMC;
//    }
//
//    private CardTemplateMC createCardTemplateMC(String cardTemplateReference, Long issuerId) {
//        CardTemplateMC cardTemplateMC = new CardTemplateMC();
//        cardTemplateMC.setTemplateReference(cardTemplateReference);
//        CardTemplate cardTemplate = cardTemplateQueries.findByNameAndIssuer(cardTemplateReference, issuerId);
//        TechnologyAndApplicationModel technologyAndApplicationModel = cardModelQueries
//                .findByTemplateAndType(cardTemplate, TechnologyAndApplicationModel.class);
//        cardTemplateMC.setTechnologyAndApplicationName(technologyAndApplicationModel.getName());
//        return cardTemplateMC;
//    }
//
//    private CardStyleMC processCardStyleMCData(RenewalCounterDTO dtoItem, CardTemplateMC currentCardTemplateMC) {
//        CardStyle dtoCardStyle = dtoItem.getCardStyle();
//        CardStyleMC currentCardStyle = null;
//        if (currentCardTemplateMC.getCardStyles() == null) {
//            currentCardTemplateMC.setCardStyles(new ArrayList<CardStyleMC>());
//        }
//        for (CardStyleMC cardStyleMC : currentCardTemplateMC.getCardStyles()) {
//            if (Objects.equals(cardStyleMC.getCardStyle(), dtoCardStyle)) {
//                currentCardStyle = cardStyleMC;
//                break;
//            }
//        }
//        if (currentCardStyle == null) {
//            currentCardStyle = createCardStyleMC(dtoCardStyle);
//            currentCardTemplateMC.getCardStyles().add(currentCardStyle);
//        }
//        return currentCardStyle;
//    }
//
//    private CardStyleMC createCardStyleMC(CardStyle cardStyle) {
//        CardStyleMC cardStyleMC = new CardStyleMC();
//        cardStyleMC.setCardStyle(cardStyle);
//        return cardStyleMC;
//    }
//
//    private ArtworkMC processArtworkMCData(RenewalCounterDTO dtoItem, CardStyleMC currentCardStyle, RenewalCounter renewalCounter) {
//        Artwork artwork = artworkQueries.findByCode(dtoItem.getArtworkCode());
//        ArtworkMC currentArtworkMC = null;
//        if (currentCardStyle.getArtworks() == null) {
//            currentCardStyle.setArtworks(new ArrayList<ArtworkMC>());
//        }
//        for (ArtworkMC artworkMC : currentCardStyle.getArtworks()) {
//            if (Objects.equals(artworkMC.getArtworkName(), artwork.getName())) {//  TODO checking with name ?
//                currentArtworkMC = artworkMC;
//                break;
//            }
//        }
//        if (currentArtworkMC == null) {
//            currentArtworkMC = createArtworkMC(artwork.getName(), renewalCounter);
//            currentCardStyle.getArtworks().add(currentArtworkMC);
//        }
//        return currentArtworkMC;
//    }
//
//    private ArtworkMC createArtworkMC(String artworkName, RenewalCounter renewalCounter) {
//        ArtworkMC artworkMC = new ArtworkMC();
//        artworkMC.setArtworkName(artworkName);
//        PeriodicCounters periodicCounters = new PeriodicCounters();
//        periodicCounters.setPeriodsMap(
//                generateInitialPeriodicCounterMap(
//                        renewalCounter.getNbPeriods(),
//                        PeriodicityEnum.valueOf(renewalCounter.getPeriodicity()),
//                        renewalCounter.getEffectiveDate()
//                )
//        );
//        periodicCounters.generatePeriodicCounters();
//        artworkMC.setPeriodicCounters(periodicCounters);
//        return artworkMC;
//    }
//
//    private Map<String, Long> generateInitialPeriodicCounterMap(int ngPeriods, PeriodicityEnum periodicityEnum, Date effectiveDate) {
//        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
//        Map<String, Long> periodicCountersMap = new HashMap<>(ngPeriods);
//        for (int i = 0; i < ngPeriods; i++) {
//            Date date;
//            if (periodicityEnum == PeriodicityEnum.MONTHLY) {
//                date = DateUtils.addMonths(effectiveDate, i);
//            } else {
//                throw new IllegalArgumentException(String.format("Generation is not supported for PeriodicityEnum of type \"%s\"", periodicityEnum));
//            }
//            periodicCountersMap.put(dateFormat.format(date), 0L);
//        }
//        return periodicCountersMap;
//    }
//
//}
