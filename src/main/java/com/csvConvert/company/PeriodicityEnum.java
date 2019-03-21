package com.csvConvert.company;

public enum PeriodicityEnum {
    MONTHLY;

    public static PeriodicityEnum lookup(String periodicity) {
        PeriodicityEnum result;
        try {
            result = PeriodicityEnum.valueOf(periodicity);
        } catch (IllegalArgumentException e) {
            // log error or something here

            throw new RuntimeException(
                    "Invalid value for enum " + e.getClass().getSimpleName() + ": " + periodicity);
        }

        return result;
    }
    }