package com.csvConvert.company;



import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import net.sf.jsefa.csv.annotation.CsvSubRecordList;
import net.sf.jsefa.rbf.annotation.Record;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
@CsvDataType(defaultPrefix = "CP")
public class CardProducerMC {

    @XmlElement
    @CsvField(pos = 1)
    private String cardProducerCode;
    @XmlElement(name = "counters")
    private PeriodicCounters periodicCounters;

    //@CsvSubRecordList(pos = 15, records = {@Record(prefix = "CT", objectType = CardTemplateMC.class)})
    //private List<CardTemplateMC> cardTemplates;

    public CardProducerMC() {
    }

    public CardProducerMC(String cardProducerCode, PeriodicCounters periodicCounters) {
        this.cardProducerCode = cardProducerCode;
        this.periodicCounters = periodicCounters;
    }

    public String getCardProducerCode() {
        return cardProducerCode;
    }

    public void setCardProducerCode(String cardProducerCode) {
        this.cardProducerCode = cardProducerCode;
    }

    public PeriodicCounters getPeriodicCounters() {
        return periodicCounters;
    }

    public void setPeriodicCounters(PeriodicCounters periodicCounters) {
        this.periodicCounters = periodicCounters;
    }


}