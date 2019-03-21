package com.csvConvert.company;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvSubRecordList;
import net.sf.jsefa.rbf.annotation.Record;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@CsvDataType(defaultPrefix = "CT")
public class CardTemplateMC {

    @XmlElement
    private String templateReference;
    @XmlElement
    private String technologyAndApplicationName;
    @XmlElement(name = "counters")
    private PeriodicCounters periodicCounters;
    @XmlElementWrapper(name = "cardStyles")
    @XmlElement(name = "cardStyle")
    @CsvSubRecordList(pos = 16, records = {@Record(prefix = "CS", objectType = CardStyleMC.class)})
    private List<CardStyleMC> cardStyles;

    public CardTemplateMC() {
    }

    public CardTemplateMC(String templateReference, String technologyAndApplicationName, PeriodicCounters periodicCounters, List<CardStyleMC> cardStyles) {
        this.templateReference = templateReference;
        this.technologyAndApplicationName = technologyAndApplicationName;
        this.periodicCounters = periodicCounters;
        this.cardStyles = cardStyles;
    }

    public String getTemplateReference() {
        return templateReference;
    }

    public void setTemplateReference(String templateReference) {
        this.templateReference = templateReference;
    }

    public String getTechnologyAndApplicationName() {
        return technologyAndApplicationName;
    }

    public void setTechnologyAndApplicationName(String technologyAndApplicationName) {
        this.technologyAndApplicationName = technologyAndApplicationName;
    }

    public PeriodicCounters getPeriodicCounters() {
        return periodicCounters;
    }

    public void setPeriodicCounters(PeriodicCounters periodicCounters) {
        this.periodicCounters = periodicCounters;
    }

    public List<CardStyleMC> getCardStyles() {
        return cardStyles;
    }

    public void setCardStyles(List<CardStyleMC> cardStyles) {
        this.cardStyles = cardStyles;
    }
}
