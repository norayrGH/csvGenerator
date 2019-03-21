package com.csvConvert.company;

import net.sf.jsefa.csv.annotation.CsvDataType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@CsvDataType(defaultPrefix = "PCS")
public class PeriodicCounters {

    @XmlElement
    private List<PeriodicCounter> periodicCounters;
    @XmlTransient
    private Map<String, Long> periodsMap;


    public PeriodicCounters() {
    }

    public PeriodicCounters(List<PeriodicCounter> periodicCounters, Map<String, Long> periodsMap) {
        this.periodicCounters = periodicCounters;
        this.periodsMap = periodsMap;
    }

    public List<PeriodicCounter> getPeriodicCounters() {
        return periodicCounters;
    }

    public void setPeriodicCounters(List<PeriodicCounter> periodicCounters) {
        this.periodicCounters = periodicCounters;
    }

    public Map<String, Long> getPeriodsMap() {
        return periodsMap;
    }

    public void setPeriodsMap(Map<String, Long> periodsMap) {
        this.periodsMap = periodsMap;
    }

    /**
     * Combines the data from local periodicCounters's List
     * with the list of all possible periods.
     * The result is a new List of PeriodicCounter objects with all possible periods.
     * @param allPeriods list of all possible periods
     * @return list of {@code PeriodicCounter} objects
     */
    public List<PeriodicCounter> generatePeriodicCounters(List<String> allPeriods) {

        List<String> existingPeriods = new ArrayList<>();
        for (PeriodicCounter counter : this.periodicCounters) {
            existingPeriods.add(counter.getPeriod());
        }

        List<PeriodicCounter> allPeriodicCounts = new ArrayList<>(this.periodicCounters);
        for (String period : allPeriods) {
            if (!existingPeriods.contains(period)) {
                allPeriodicCounts.add(new PeriodicCounter(period, 0L));
            }
        }

        Comparator periodComparator = new Comparator<PeriodicCounter>() {
            @Override
            public int compare(PeriodicCounter o1, PeriodicCounter o2) {
                String o1Month = o1.getPeriod().substring(0, 2);
                String o2Month = o2.getPeriod().substring(0, 2);

                String o1Year = o1.getPeriod().substring(3);
                String o2Year = o2.getPeriod().substring(3);

                if ((!o1Year.equals(o2Year))) { return o1Year.compareTo(o2Year); }
                return o1Month.compareTo(o2Month);
            }
        };
        Collections.sort(allPeriodicCounts, periodComparator);
        return allPeriodicCounts;
    }

}
