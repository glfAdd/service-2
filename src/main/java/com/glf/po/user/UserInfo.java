package com.glf.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author glfadd
 */
@Data
@TableName("user_info")
public class UserInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String phone;
}
