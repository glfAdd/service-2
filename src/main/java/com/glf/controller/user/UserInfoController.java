package com.glf.controller.user;

import com.glf.client.UserClient;
import com.glf.mapper.db1.UserInfoMapper;
import com.glf.po.user.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author glfadd
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户信息")
public class UserInfoController {

    /**
     * Feign 发送 http 请求
     */
    @Resource
    private UserClient userClient;

    /**
     * RestTemplate 发送 http 请求
     */
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    @Qualifier("redisPool2")
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("request1")
    public String request1() {
        UserInfo a = userInfoMapper.selectList(null).get(0);
        return a.toString();
    }

    @GetMapping("request2")
    public String request2() {
        stringRedisTemplate.opsForValue().set("kkkk", "123");
        return "123aaaa12";
    }

    @GetMapping("request3")
    public String request3() {
        String aaa = restTemplate.getForObject("https://www.baidu.com/baidu?ie=utf-8&wd=123", String.class);
        return "11111111";
    }

    @GetMapping("request4")
    public String request4() {
        String aaa = userClient.requestTest1();
        return "ccccccccccc";
    }
}
