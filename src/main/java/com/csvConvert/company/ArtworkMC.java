package com.csvConvert.company;

import net.sf.jsefa.csv.annotation.CsvDataType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@CsvDataType(defaultPrefix = "AW")
public class ArtworkMC {

    @XmlElement
    private String artworkName;
    @XmlElement(name = "counters")
    private PeriodicCounters periodicCounters;

    public ArtworkMC() {
    }

    public ArtworkMC(String artworkName, PeriodicCounters periodicCounters) {
        this.artworkName = artworkName;
        this.periodicCounters = periodicCounters;
    }

    public String getArtworkName() {
        return artworkName;
    }

    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }

    public PeriodicCounters getPeriodicCounters() {
        return periodicCounters;
    }

    public void setPeriodicCounters(PeriodicCounters periodicCounters) {
        this.periodicCounters = periodicCounters;
    }
}
