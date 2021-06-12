package com.raport.domain.enums;

public enum FileType {
    EXCEL("EXCEL"),
    PDF("PDF");

    private String name;

    FileType(String label) {
        this.name = label;
    }

    public String getName() {
        return name;
    }
}
