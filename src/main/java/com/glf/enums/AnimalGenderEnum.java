package com.glf.enums;

import lombok.Getter;

/**
 * @author glfadd
 */
@Getter
public enum AnimalGenderEnum {
    MU(0, "母"),
    GONG(1, "公");

    private final Integer code;
    private final String value;

    AnimalGenderEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
