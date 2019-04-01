package com.csvConvert.company.extractcardrenewalcounter.enums;

public enum OutputFormatEnum {
    XML(".xml"),
    CSV(".csv");

    private final String extension;

    OutputFormatEnum(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
