package com.csvConvert.company.extractcardrenewalcounter.xml;


import com.csvConvert.company.extractcardrenewalcounter.helper.PeriodicCounterComparator;
import net.sf.jsefa.xml.annotation.XmlDataType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@XmlDataType
@XmlAccessorType(XmlAccessType.FIELD)
public class PeriodicCounters {

    @XmlElement(name = "periodicCounter")
    private List<PeriodicCounter> periodicCounterList;
    @XmlTransient
    private Map<String, Long> periodsMap;


    public PeriodicCounters() {
    }

    public PeriodicCounters(List<PeriodicCounter> periodicCounterList, Map<String, Long> periodsMap) {
        this.periodicCounterList = periodicCounterList;
        this.periodsMap = periodsMap;
    }

    public List<PeriodicCounter> getPeriodicCounterList() {
        return periodicCounterList;
    }

    public void setPeriodicCounterList(List<PeriodicCounter> periodicCounterList) {
        this.periodicCounterList = periodicCounterList;
    }

    public Map<String, Long> getPeriodsMap() {
        return periodsMap;
    }

    public void setPeriodsMap(Map<String, Long> periodsMap) {
        this.periodsMap = periodsMap;
    }

    public void generatePeriodicCounters() {
        this.periodicCounterList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : periodsMap.entrySet()) {
            this.periodicCounterList.add(new PeriodicCounter(entry.getKey(), entry.getValue()));
        }
    }

    public void generateAndSortPeriodicCounters() {
        generatePeriodicCounters();
        Collections.sort(periodicCounterList, new PeriodicCounterComparator());
    }
}
