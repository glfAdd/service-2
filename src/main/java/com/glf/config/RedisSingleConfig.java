package com.glf.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 单节点配置
 *
 * @author glfadd
 */
@Configuration
public class RedisSingleConfig {

    @Value("${spring.redis1.host:127.0.0.1}")
    private String host;
    @Value("${spring.redis1.port:6379}")
    private int port;
    @Value("${spring.redis1.password:123456789}")
    private String password;
    @Value("${spring.redis1.timeout:6000}")
    private int timeout;
    @Value("${spring.redis1.database:0}")
    private int database;

    // pool映射
    @Value("${spring.redis1.lettuce.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis1.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis1.lettuce.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis1.lettuce.pool.max-wait}")
    private long maxWait;

    //读取pool配置
    @Bean
    public GenericObjectPoolConfig redisPool2() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMinIdle(minIdle);
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(maxWait);
        return config;
    }

    @Bean
    public RedisStandaloneConfiguration redisConfig2() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(host, port);
        redisConfig.setPassword(RedisPassword.of(password));
        return redisConfig;
    }

    @Bean("factory2")
    public LettuceConnectionFactory factory2(@Qualifier("redisPool2") GenericObjectPoolConfig config,
                                             @Qualifier("redisConfig2") RedisStandaloneConfiguration redisConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
        return new LettuceConnectionFactory(redisConfig, clientConfiguration);
    }


    @Bean("redisTemplate2")
    public RedisTemplate<String, Object> redisTemplate2(@Qualifier("factory2") LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        //设置序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

}
