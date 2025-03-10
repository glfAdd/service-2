package com.glf.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author glfadd
 */
@Configuration
// basePackages 指向的是你存放数据库 db1 的 mapper 的包
@MapperScan(basePackages = "com.glf.mapper.db1", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class DataSourceDB1Config {

    @Bean(name = "db1DataSource")
    // 指向yml配置文件中的数据库配置
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    // 主库加这个注解，修改优先权，表示发现相同类型bean，优先使用该方法。
    @Primary
    public DataSource dbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory defSqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db1/*xml"));
//        return bean.getObject();

        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 设置 mybatis 的xml所在位置
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db1/*xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager dbTransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate dbSqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws
            Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

