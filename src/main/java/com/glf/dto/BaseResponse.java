package com.glf.dto;

import com.glf.enums.ErrorEnum;
import lombok.Data;

/**
 * @author glfadd
 */
@Data
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;

    public BaseResponse(int i, String success, T data) {
        this.code = i;
        this.message = success;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(ErrorEnum.SUCCESS.getErrorCode(), ErrorEnum.SUCCESS.getErrorMsg(), data);
    }

    public static <T> BaseResponse<T> error(ErrorEnum error, T data) {
        return new BaseResponse<>(error.getErrorCode(), error.getErrorMsg(), data);
    }

}
