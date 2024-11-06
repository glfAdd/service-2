package com.glf.enums;

import lombok.Getter;

/**
 * @author glfadd
 */
@Getter
public enum ErrorEnum {
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    VALID_ERROR(10000, "参数验证失败");

    private final Integer errorCode;
    private final String errorMsg;

    ErrorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
