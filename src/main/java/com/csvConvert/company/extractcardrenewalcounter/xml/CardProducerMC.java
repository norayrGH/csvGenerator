package com.csvConvert.company.extractcardrenewalcounter.xml;



import net.sf.jsefa.xml.annotation.XmlDataType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlDataType
@XmlAccessorType(XmlAccessType.FIELD)
public class CardProducerMC extends AbstractUpdatableRenewalCounter<CardTemplateMC> {

    @XmlElement
    private String cardProducerCode;

    @XmlElementWrapper(name = "cardTemplates")
    @XmlElement(name = "cardTemplate")
    private List<CardTemplateMC> cardTemplates;

    public CardProducerMC() {
    }

    public CardProducerMC(String cardProducerCode, List<CardTemplateMC> cardTemplates) {
        this.cardProducerCode = cardProducerCode;
        this.cardTemplates = cardTemplates;
    }

    @Override
    protected List<CardTemplateMC> getChildrenList() {
        return this.cardTemplates;
    }

    public String getCardProducerCode() {
        return cardProducerCode;
    }

    public void setCardProducerCode(String cardProducerCode) {
        this.cardProducerCode = cardProducerCode;
    }

    public List<CardTemplateMC> getCardTemplates() {
        return cardTemplates;
    }

    public void setCardTemplates(List<CardTemplateMC> cardTemplates) {
        this.cardTemplates = cardTemplates;
    }
}
