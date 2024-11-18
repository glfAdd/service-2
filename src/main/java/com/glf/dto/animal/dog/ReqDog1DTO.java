package com.glf.dto.animal.dog;

import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import java.util.List;

/**
 * @author glfadd
 */
@Data
public class ReqDog1DTO {

    @NonNull
    private String name;
    @NonNull
    private Integer age;
    @NonNull
    private String gender;
    @NonNull
    private Double weight;
    @NonNull
    private Double height;
    @Valid
    private List<ActionItem> actionItemList;

    @Data
    static public class ActionItem {
        @NonNull
        private Integer actionCode;
        @NonNull
        private String actionName;
    }

}
