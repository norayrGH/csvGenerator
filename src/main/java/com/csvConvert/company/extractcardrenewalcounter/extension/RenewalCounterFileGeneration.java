package com.csvConvert.company.extractcardrenewalcounter.extension;

import com.csvConvert.company.extractcardrenewalcounter.xml.RenewalCounter;

public interface RenewalCounterFileGeneration extends Behaviour {
    void generateFile(RenewalCounter renewalCounter, String fileName);
    String getName();
}
