package com.csvConvert.company.extractcardrenewalcounter.xml;



import net.sf.jsefa.xml.annotation.XmlDataType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlDataType
@XmlAccessorType(XmlAccessType.FIELD)
public class CardTemplateMC extends AbstractUpdatableRenewalCounter<CardStyleMC> {

    @XmlElement
    private String templateReference;
    @XmlElement
    private String technologyAndApplicationName;
    @XmlElementWrapper(name = "cardStyles")
    @XmlElement(name = "cardStyle")
    private List<CardStyleMC> cardStyles;

    public CardTemplateMC() {
    }

    public CardTemplateMC(String templateReference, String technologyAndApplicationName, List<CardStyleMC> cardStyles) {
        this.templateReference = templateReference;
        this.technologyAndApplicationName = technologyAndApplicationName;
        this.cardStyles = cardStyles;
    }

    @Override
    protected List<CardStyleMC> getChildrenList() {
        return this.cardStyles;
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

    public List<CardStyleMC> getCardStyles() {
        return cardStyles;
    }

    public void setCardStyles(List<CardStyleMC> cardStyles) {
        this.cardStyles = cardStyles;
    }
}
