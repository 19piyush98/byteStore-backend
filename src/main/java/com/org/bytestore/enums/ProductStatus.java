package com.org.bytestore.enums;

public enum ProductStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;

    ProductStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

