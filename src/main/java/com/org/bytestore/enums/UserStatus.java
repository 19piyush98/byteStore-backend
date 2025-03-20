package com.org.bytestore.enums;

public enum UserStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;

    UserStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

