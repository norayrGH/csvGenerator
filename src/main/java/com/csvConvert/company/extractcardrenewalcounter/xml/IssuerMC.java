package com.csvConvert.company.extractcardrenewalcounter.xml;


import net.sf.jsefa.xml.annotation.XmlDataType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlDataType
@XmlAccessorType(XmlAccessType.FIELD)
public class IssuerMC extends AbstractUpdatableRenewalCounter<CardProducerMC> {

    @XmlElement
    private Long issuerId;

    @XmlElement
    private String issuerName;

    @XmlElement
    private Long parentIssuerId;

    @XmlElementWrapper(name = "cardProducers")
    @XmlElement(name = "cardProducer")
    private List<CardProducerMC> cardProducers;


    public IssuerMC() {
    }

    public IssuerMC(Long issuerId, String issuerName, Long parentIssuerId, List<CardProducerMC> cardProducers) {
        this.issuerId = issuerId;
        this.issuerName = issuerName;
        this.parentIssuerId = parentIssuerId;
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

    public Long getParentIssuerId() {
        return parentIssuerId;
    }

    public void setParentIssuerId(Long parentIssuerId) {
        this.parentIssuerId = parentIssuerId;
    }

    public List<CardProducerMC> getCardProducers() {
        return cardProducers;
    }

    public void setCardProducers(List<CardProducerMC> cardProducers) {
        this.cardProducers = cardProducers;
    }

    @Override
    protected List<CardProducerMC> getChildrenList() {
        return this.cardProducers;
    }

}
