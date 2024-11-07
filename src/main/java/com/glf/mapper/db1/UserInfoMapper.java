package com.glf.mapper.db1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glf.po.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.convert.DataSizeUnit;
import com.baomidou.dynamic.datasource.annotation.DS;

/**
 * @author glfadd
 */
@Mapper
@DS("db1")
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
