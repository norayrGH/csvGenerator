package com.csvConvert.company;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvSubRecordList;
import net.sf.jsefa.rbf.annotation.Record;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@CsvDataType(defaultPrefix = "CS")
public class CardStyleMC {

    @XmlElement
    private String cardStyleName;
    @XmlElement(name="counters")
    private PeriodicCounters periodicCounters;
    @XmlElementWrapper(name = "artworks")
    @XmlElement(name = "artwork")
    @CsvSubRecordList(pos = 17, records = {@Record(prefix = "AW", objectType = ArtworkMC.class)})
    private List<ArtworkMC> artworks;

    public CardStyleMC() {
    }

    public CardStyleMC(String cardStyleName, PeriodicCounters periodicCounters, List<ArtworkMC> artworks) {
        this.cardStyleName = cardStyleName;
        this.periodicCounters = periodicCounters;
        this.artworks = artworks;
    }

    public String getCardStyleName() {
        return cardStyleName;
    }

    public void setCardStyleName(String cardStyleName) {
        this.cardStyleName = cardStyleName;
    }

    public PeriodicCounters getPeriodicCounters() {
        return periodicCounters;
    }

    public void setPeriodicCounters(PeriodicCounters periodicCounters) {
        this.periodicCounters = periodicCounters;
    }

    public List<ArtworkMC> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkMC> artworks) {
        this.artworks = artworks;
    }
}
