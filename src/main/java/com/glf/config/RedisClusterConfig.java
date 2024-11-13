package com.glf.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

/**
 * 集群配置
 *
 * @author glfadd
 */
//@Configuration
public class RedisClusterConfig {
    @Value("${spring.redis2.host:127.0.0.1}")
    private String host;
    @Value("${spring.redis2.port:6379}")
    private int port;
    @Value("${spring.redis2.password:123456789}")
    private String password;
    @Value("${spring.redis2.timeout:6000}")
    private int timeout;
    @Value("${spring.redis2.database:0}")
    private int database;

    //pool映射
    @Value("${spring.redis2.lettuce.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis2.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis2.lettuce.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis2.lettuce.pool.max-wait}")
    private int maxWait;

    //cluster映射
    List<String> nodes;

    @Value("${spring.redis.cluster.max-redirects:3}")
    private int maxRedirects;


    @Bean
    public GenericObjectPoolConfig redisPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMinIdle(minIdle);
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(maxWait);
        return config;
    }

    /**
     * 集群配置类
     *
     * @return
     */
    @Bean
    public RedisClusterConfiguration redisConfig() {
        RedisClusterConfiguration redisConfig = new RedisClusterConfiguration(nodes);
        redisConfig.setMaxRedirects(maxRedirects);
        redisConfig.setPassword(RedisPassword.of(password));
        return redisConfig;
    }

    @Bean("factory")
    @Primary
    public LettuceConnectionFactory factory(@Qualifier("redisPool") GenericObjectPoolConfig config,
                                            @Qualifier("redisConfig") RedisClusterConfiguration redisConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
        return new LettuceConnectionFactory(redisConfig, clientConfiguration);
    }

    /**
     * 集群redis数据源
     *
     * @param connectionFactory
     * @return
     */
    @Bean("redisTemplate")
    @Primary
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("factory") LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        //设置序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
