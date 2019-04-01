//package com.csvConvert.company.extractcardrenewalcounter;
//
//import com.worldline.sdco.easyflow.imex.JobContext;
//import com.worldline.toolbox.easyflow.iterators.batch.BatchIterable;
//import net.atos.wlp.cms.batches.configuration.BatchServiceSettings;
//import net.atos.wlp.cms.batches.extractcardorder.TaskProcessorImpl;
//import net.atos.wlp.cms.batches.extractcardrenewalcounter.enums.DetailEnum;
//import net.atos.wlp.cms.batches.extractcardrenewalcounter.enums.OutputFormatEnum;
//import net.atos.wlp.cms.batches.extractcardrenewalcounter.enums.PeriodicityEnum;
//import net.atos.wlp.cms.batches.extractcardrenewalcounter.extension.RenewalCounterFileGeneration;
//import net.atos.wlp.cms.batches.extractcardrenewalcounter.xml.RenewalCounter;
//import net.atos.wlp.cms.core.RenewalCounterDTO;
//import net.atos.wlp.cms.core.cardmanagement.card.CardQueries;
//import net.atos.wlp.cms.core.utils.DateUtils;
//import net.atos.xa.resourcelocator.ResourceLocator;
//import net.atos.xa.tool.extensibility.BehaviourFactory;
//import org.apache.log4j.Logger;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class RenewalCounterTaskProcessorImpl extends TaskProcessorImpl {
//
//    private static final Logger logger = Logger.getLogger(RenewalCounterTaskProcessorImpl.class);
//
//    private static final String START_DATE_FORMAT = "yyyyMMddmmsshh"; //TODO
//
//    private static final String EXTRACT_DATE_FORMAT = "yyyyMMddHHmmss";
//
//    private static final String EFFECTIVE_DATE_FORMAT = "yyyyMMdd";
//
//    private final CardQueries cardQueries = ResourceLocator.lookup(CardQueries.class);
//
//    @Override
//    public Iterable onInitialize(JobContext context) throws Exception {
//
//        RenewalCounter renewalCounter = createRenewalCounter(context);
//
//        context.setVariable(RenewalCounterConstants.RENEWAL_COUNTER, renewalCounter);
//
//        String cardProducer = context.getVariable(RenewalCounterConstants.CARD_PRODUCER, String.class);
//        cardProducer = "ALL".equals(cardProducer) ? null : cardProducer;
//
//        List<RenewalCounterDTO> renewalCounterDTOS = cardQueries.findForMonthCounterRenewal(renewalCounter.getEffectiveDate(),
//                DateUtils.addMonths(renewalCounter.getEffectiveDate(), context.getVariable(RenewalCounterConstants.NB_PERIODS, Integer.class)),
//                context.getVariable(RenewalCounterConstants.ISSUER_ID, Long.class), cardProducer);
//
//        //  TODO RenewalCounterBatchLauncher  ->  commitInterval  -> batchSize
//        //   BatchIterable(final Iterable<T> iterable, final int batchSize) ?
//        return new BatchIterable<>(renewalCounterDTOS, context.getCommitInterval());
//    }
//
//    @Override
//    public void onFinalize(JobContext context) throws Exception {
//
//        RenewalCounter renewalCounter = context.getVariable(RenewalCounterConstants.RENEWAL_COUNTER, RenewalCounter.class);
//
//        if (PeriodicityEnum.MONTHLY.equals(PeriodicityEnum.valueOf(renewalCounter.getPeriodicity()))) {
//
//            renewalCounter.setPeriods(generateAllPeriods(renewalCounter.getEffectiveDate(), context.getVariable(RenewalCounterConstants.NB_PERIODS, Integer.class)));
//
//            if (renewalCounter.getIssuers() != null) {
//                renewalCounter.triggerAllChildPeriodicCountersUpdate();
//            }
//
//            RenewalCounterFileGeneration renewalCounterFileGeneration = BehaviourFactory.getInstance()
//                    .getBehaviour(RenewalCounterFileGeneration.class, renewalCounter.getOutputFormat());
//
//            Long issuerId = context.getVariable(RenewalCounterConstants.ISSUER_ID, Long.class);
//            if (issuerId != null) {
//                renewalCounterFileGeneration.generateFile(renewalCounter, String.format(generateOutputFileName(context.getJobName(),
//                        context.getVariable(RenewalCounterConstants.EFFECTIVE_DATE, Date.class)).toString(), issuerId)); // TODO format yyyyMMddmmsshh ?
//            } else {
//                renewalCounterFileGeneration.generateFile(renewalCounter, String.format(generateOutputFileName(context.getJobName(),
//                        context.getVariable(RenewalCounterConstants.EFFECTIVE_DATE, Date.class)).toString(), "ALL"));// TODO format yyyyMMddmmsshh ?
//            }
//        }
//    }
//
//    @Override
//    public void onError(JobContext context, Exception exception) throws Exception {
//        logger.error("Error occurred when processing  Renewal Counter task processor.");
//    }
//
//    private RenewalCounter createRenewalCounter(JobContext context) {
//        Long issuerId = context.getVariable(RenewalCounterConstants.ISSUER_ID, Long.class);
//        RenewalCounter renewalCounter = new RenewalCounter();
//        renewalCounter.setExtractDate(new Date());
//        renewalCounter.setExtractReference(
//                RenewalCounterConstants.RC + new SimpleDateFormat(EXTRACT_DATE_FORMAT).format(renewalCounter.getExtractDate()));
//        renewalCounter.setEffectiveDate(context.getVariable(RenewalCounterConstants.EFFECTIVE_DATE, Date.class));
//        renewalCounter.setCardProducer(context.getVariable(RenewalCounterConstants.CARD_PRODUCER, String.class));
//
//        // TODO String issuer= “issuerId” from context ?
//        if (issuerId == null) {
//            renewalCounter.setIssuer(null);
//        } else {
//            renewalCounter.setIssuer(String.valueOf(issuerId));
//        }
//
//        renewalCounter.setPeriodicity(context.getVariable(RenewalCounterConstants.PERIODICITY, PeriodicityEnum.class).name());
//        renewalCounter.setDetails(context.getVariable(RenewalCounterConstants.DETAILS, DetailEnum.class).name());
//        renewalCounter.setNbPeriods(context.getVariable(RenewalCounterConstants.NB_PERIODS, Integer.class));
//        renewalCounter.setOutputFormat(context.getVariable(RenewalCounterConstants.OUTPUT_FORMAT, OutputFormatEnum.class).name());
//
//        return renewalCounter;
//    }
//
//    private StringBuilder generateOutputFileName(String jobName, Date startDate) {
//        return new StringBuilder()
//                .append(BatchServiceSettings.getInstance().getReportPath())
//                .append("/IBO_CMS_3012_")
//                .append(jobName)
//                .append("_%s_")
//                .append(new SimpleDateFormat(START_DATE_FORMAT).format(startDate)); // TODO change to existing format
//    }
//
//    private List<String> generateAllPeriods(Date effectiveDate, int ngPeriods) {
//        final SimpleDateFormat dateFormat = new SimpleDateFormat(EFFECTIVE_DATE_FORMAT);
//        List<String> allPeriods = new ArrayList<>();
//        for (int i = 0; i < ngPeriods; i++) {
//            Date nextDate = DateUtils.addMonths(effectiveDate, i);
//            String nextDateFormatted = dateFormat.format(nextDate);
//            allPeriods.add(String.format("%s/%s", nextDateFormatted.substring(4, 6), nextDateFormatted.substring(2, 4)));
//        }
//
//        return allPeriods;
//    }
//}