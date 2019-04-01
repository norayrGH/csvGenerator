package com.csvConvert.company.extractcardrenewalcounter.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractRenewalCounter implements WithPeriodicCounters {

    @XmlElement(name = "counters")
    protected PeriodicCounters periodicCounters = new PeriodicCounters();

    @Override
    public PeriodicCounters getPeriodicCounters() {
        return periodicCounters;
    }

    @Override
    public void setPeriodicCounters(PeriodicCounters periodicCounters) {
        this.periodicCounters = periodicCounters;
    }
}
