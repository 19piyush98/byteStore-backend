package com.org.bytestore.enums;

public enum OperationType {
    CREATE("CREATE"),
    UPDATE("UPDATE"),

    DELETE("DELETE");

    private final String value;

    OperationType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
