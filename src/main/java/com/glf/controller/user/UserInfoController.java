package com.glf.controller.user;

import com.glf.mapper.db1.UserInfoMapper;
import com.glf.po.user.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Resource
    @Qualifier("redisPool2")
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("info")
    public String getUserInfo() {
        UserInfo a = userInfoMapper.selectList(null).get(0);
        return a.toString();
    }

    @GetMapping("detail")
    public String getUserDetail() {
        stringRedisTemplate.opsForValue().set("kkkk", "123");
        return "12312";
    }
}
