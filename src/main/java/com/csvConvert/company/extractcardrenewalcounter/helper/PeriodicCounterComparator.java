package com.csvConvert.company.extractcardrenewalcounter.helper;


import com.csvConvert.company.extractcardrenewalcounter.xml.PeriodicCounter;

import java.util.Comparator;

public class PeriodicCounterComparator implements Comparator<PeriodicCounter> {
    @Override
    public int compare(PeriodicCounter o1, PeriodicCounter o2) {
        String o1Month = o1.getPeriod().substring(0, 2);
        String o2Month = o2.getPeriod().substring(0, 2);

        String o1Year = o1.getPeriod().substring(3);
        String o2Year = o2.getPeriod().substring(3);

        if ((!o1Year.equals(o2Year))) { return o1Year.compareTo(o2Year); }
        return o1Month.compareTo(o2Month);
    }
}
