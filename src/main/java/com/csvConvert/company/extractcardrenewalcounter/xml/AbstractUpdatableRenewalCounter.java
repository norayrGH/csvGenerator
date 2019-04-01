package com.csvConvert.company.extractcardrenewalcounter.xml;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractUpdatableRenewalCounter<T extends WithPeriodicCounters> extends AbstractRenewalCounter
        implements UpdatablePeriodCounters {

    protected abstract List<T> getChildrenList();

    @Override
    public void updatePeriodicCounters() {
        periodicCounters.setPeriodsMap(new HashMap<String, Long>());
        for (T item : getChildrenList()) {
            if(item instanceof UpdatablePeriodCounters) {
                ((UpdatablePeriodCounters) item).updatePeriodicCounters();
            }

            for (PeriodicCounter counter : item.getPeriodicCounters().getPeriodicCounterList()) {
                Long currentCount = item.getPeriodicCounters().getPeriodsMap()
                        .get(counter.getPeriod());
                item.getPeriodicCounters().getPeriodsMap()
                        .put(counter.getPeriod(), currentCount + counter.getCount());
            }
        }
        periodicCounters.generateAndSortPeriodicCounters();
    }
}
