package com.csvConvert.company;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import net.sf.jsefa.csv.annotation.CsvSubRecordList;
import net.sf.jsefa.rbf.annotation.Record;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CsvDataType(defaultPrefix = "RC")
public class RenewalCounter {
    @XmlElement
    @CsvField(pos = 1)
    private String extractReference;
    @XmlElement
    @CsvField(pos = 4)
    private String cardProducer;
    @XmlElement
    @CsvField(pos = 5)
    private String issuer;
    @XmlElement
    @CsvField(pos = 6)
    private String details;
    @XmlElement
    @CsvField(pos = 7)
    private Integer nbPeriods;
    @XmlElement
    @CsvField(pos = 8)
    private String periodicity;
    @XmlElement
    @CsvField(pos = 9)
    private String outputFormat;
    @XmlElementWrapper(name = "issuers")
    @XmlElement(name = "issuer")
    @CsvSubRecordList(pos = 10, records = {@Record(prefix = "IS", objectType = IssuerMC.class)})
    private List<IssuerMC> issuers = new ArrayList<>();
    @XmlTransient
    @CsvField(pos=11)
    private List<String> periods;

    public RenewalCounter() {
    }

    public RenewalCounter(String extractReference, String cardProducer, String issuer, String details, Integer nbPeriods, String periodicity, String outputFormat, List<IssuerMC> issuers, List<String> periods) {
        this.extractReference = extractReference;
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
}
