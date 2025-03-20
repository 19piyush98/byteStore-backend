package com.org.bytestore.enums;

public enum OrganisationStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;

    OrganisationStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
