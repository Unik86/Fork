package com.fork.base.model.enums;

public enum ParseType {

    ALL("All"),
    ONLY_LIVE("OnlyLive"),
    WITHOUT_LIVE("WithoutLive");

    private String type;

    ParseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean isEquals(String type) {
        return this.type.equals(type);
    }
}
