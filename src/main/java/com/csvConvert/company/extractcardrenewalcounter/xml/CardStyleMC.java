package com.csvConvert.company.extractcardrenewalcounter.xml;


import net.sf.jsefa.xml.annotation.XmlDataType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlDataType
@XmlAccessorType(XmlAccessType.FIELD)
public class CardStyleMC extends AbstractUpdatableRenewalCounter<ArtworkMC> {

    @XmlElement(name = "cardStyleName")
    private CardStyle cardStyle;
    @XmlElementWrapper(name = "artworks")
    @XmlElement(name = "artwork")
    private List<ArtworkMC> artworks;

    public CardStyleMC() {
    }

    public CardStyleMC(CardStyle cardStyle, List<ArtworkMC> artworks) {
        this.cardStyle = cardStyle;
        this.artworks = artworks;
    }

    @Override
    protected List<ArtworkMC> getChildrenList() {
        return this.artworks;
    }

    public CardStyle getCardStyle() {
        return cardStyle;
    }

    public void setCardStyle(CardStyle cardStyle) {
        this.cardStyle = cardStyle;
    }

    public List<ArtworkMC> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkMC> artworks) {
        this.artworks = artworks;
    }
}
