package com.csvConvert.company.extractcardrenewalcounter.enums;

public enum DetailEnum {

    NO("No"),

    PER_TEMPLATES("Per Card Templates"),

    PER_TEMPLATES_STYLES("Per Card Templates - Card Styles"),

    PER_TEMPLATES_STYLES_ARTWORKS("Per Card Templates - Card Styles - Artworks");

    private final String value;

    DetailEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}