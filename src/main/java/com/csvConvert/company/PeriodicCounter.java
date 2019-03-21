package com.csvConvert.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PeriodicCounter {
    @XmlElement
    private String period;
    @XmlElement
    private Long count;

    public PeriodicCounter(String period, Long count) {
        this.period = period;
        this.count = count;
    }

    public PeriodicCounter() { }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
