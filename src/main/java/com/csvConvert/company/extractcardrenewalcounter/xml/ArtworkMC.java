package com.csvConvert.company.extractcardrenewalcounter.xml;

import net.sf.jsefa.xml.annotation.XmlDataType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlDataType
@XmlAccessorType(XmlAccessType.FIELD)
public class ArtworkMC extends AbstractRenewalCounter {

    @XmlElement
    private String artworkName;

    public ArtworkMC() {
    }

    public ArtworkMC(String artworkName) {
        this.artworkName = artworkName;
    }

    public String getArtworkName() {
        return artworkName;
    }

    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }

}
