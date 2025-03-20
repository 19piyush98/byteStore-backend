package com.org.bytestore.enums;

public enum OperationEntity {
    USER("USER"),
    PRODUCT("PRODUCT"),

    ORG("ORG");

    private final String value;

    OperationEntity(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
