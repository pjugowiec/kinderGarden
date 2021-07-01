package com.report.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public enum DateType {
    Uw("Uw", 38),
    Um("Um", 39),
    Ub("Ub", 40),
    Usz("Usz", 41),
    Uo("Uo", 42),
    Ch("Ch", 43),
    NN("NN", 44),
    Nup("Nup", 45),
    Nun("Nun", 46),
    Del("Del", 47),
    Inne("Inne", 48);

    @Getter
    private String value;

    @Getter
    private Integer index;

    public boolean equalsName(String otherName) {
        return value.equals(otherName);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
