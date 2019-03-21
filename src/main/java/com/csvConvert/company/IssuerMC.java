package com.csvConvert.company;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import net.sf.jsefa.csv.annotation.CsvSubRecordList;
import net.sf.jsefa.rbf.annotation.Record;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@CsvDataType(defaultPrefix = "IS")
public class IssuerMC {

    @XmlElement
    @CsvField(pos = 11 )
    private Long issuerId;
    @XmlElement
    @CsvField(pos = 12)
    private String issuerName;
    @XmlElementWrapper(name = "cardProducers")
    @XmlElement(name = "cardProducer")
    @CsvSubRecordList(pos = 13, records = {@Record(prefix = "CP", objectType = CardProducerMC.class)})
    private List<CardProducerMC> cardProducers;

    public IssuerMC() {


    }

    public IssuerMC(Long issuerId, String issuerName, List<CardProducerMC> cardProducers) {
        this.issuerId = issuerId;
        this.issuerName = issuerName;
        this.cardProducers = cardProducers;
    }

    public Long getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Long issuerId) {
        this.issuerId = issuerId;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public List<CardProducerMC> getCardProducers() {
        return cardProducers;
    }

    public void setCardProducers(List<CardProducerMC> cardProducers) {
        this.cardProducers = cardProducers;
    }
}