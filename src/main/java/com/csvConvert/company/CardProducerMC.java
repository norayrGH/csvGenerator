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
    @CsvField(pos = 14)
    private String cardProducerCode;
    @XmlElement(name = "counters")
    private PeriodicCounters periodicCounters;
    @XmlElementWrapper(name = "cardTemplates")
    @XmlElement(name = "cardTemplate")
    @CsvSubRecordList(pos = 15, records = {@Record(prefix = "CT", objectType = CardTemplateMC.class)})
    private List<CardTemplateMC> cardTemplates;

    public CardProducerMC() {
    }

    public CardProducerMC(String cardProducerCode, PeriodicCounters periodicCounters, List<CardTemplateMC> cardTemplates) {
        this.cardProducerCode = cardProducerCode;
        this.periodicCounters = periodicCounters;
        this.cardTemplates = cardTemplates;
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

    public List<CardTemplateMC> getCardTemplates() {
        return cardTemplates;
    }

    public void setCardTemplates(List<CardTemplateMC> cardTemplates) {
        this.cardTemplates = cardTemplates;
    }
}