package com.csvConvert.company.extractcardrenewalcounter.xml;



import net.atos.xa.eff.adapters.DateAdapter;
import net.atos.xa.eff.adapters.DateTimeAdapter;
import net.sf.jsefa.xml.annotation.XmlDataType;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlDataType
@XmlAccessorType(XmlAccessType.FIELD)
public class RenewalCounter {
    @XmlElement
    private String extractReference;
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date extractDate;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date effectiveDate;
    @XmlElement
    private String cardProducer;
    @XmlElement
    private String issuer;
    @XmlElement
    private String details;
    @XmlElement
    private Integer nbPeriods;
    @XmlElement
    private String periodicity;
    @XmlElement
    private String outputFormat;
    @XmlElementWrapper(name = "issuers")
    @XmlElement(name = "issuer")
    private List<IssuerMC> issuers;
    @XmlTransient
    private List<String> periods;

    public RenewalCounter() {
    }

    public RenewalCounter(String extractReference, Date extractDate, Date effectiveDate, String cardProducer, String issuer, String details, Integer nbPeriods, String periodicity, String outputFormat, List<IssuerMC> issuers, List<String> periods) {
        this.extractReference = extractReference;
        this.extractDate = extractDate;
        this.effectiveDate = effectiveDate;
        this.cardProducer = cardProducer;
        this.issuer = issuer;
        this.details = details;
        this.nbPeriods = nbPeriods;
        this.periodicity = periodicity;
        this.outputFormat = outputFormat;
        this.issuers = issuers;
        this.periods = periods;
    }

    public String getExtractReference() {
        return extractReference;
    }

    public void setExtractReference(String extractReference) {
        this.extractReference = extractReference;
    }

    public Date getExtractDate() {
        return extractDate;
    }

    public void setExtractDate(Date extractDate) {
        this.extractDate = extractDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getCardProducer() {
        return cardProducer;
    }

    public void setCardProducer(String cardProducer) {
        this.cardProducer = cardProducer;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getNbPeriods() {
        return nbPeriods;
    }

    public void setNbPeriods(Integer nbPeriods) {
        this.nbPeriods = nbPeriods;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public List<IssuerMC> getIssuers() {
        return issuers;
    }

    public void setIssuers(List<IssuerMC> issuers) {
        this.issuers = issuers;
    }

    public List<String> getPeriods() {
        return periods;
    }

    public void setPeriods(List<String> periods) {
        this.periods = periods;
    }

    public void triggerAllChildPeriodicCountersUpdate() {
        for (IssuerMC issuerMC : issuers) {
            issuerMC.updatePeriodicCounters();
        }
    }
}
