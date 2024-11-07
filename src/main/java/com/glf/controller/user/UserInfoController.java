package com.glf.controller.user;

import com.glf.mapper.db1.UserInfoMapper;
import com.glf.po.user.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author glfadd
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户信息")
public class UserInfoController {

    @Resource
    private UserInfoMapper userInfoMapper;

    @GetMapping("info")
    public String getUserInfo() {
        UserInfo a = userInfoMapper.selectList(null).get(0);
        return a.toString();
    }
}
