package com.glf.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * 哨兵配置
 *
 * @author glfadd
 */
//@Configuration
public class RedisSentryConfig {
    @Value("${spring.redis3.host:127.0.0.1}")
    private String host;
    @Value("${spring.redis3.port:6379}")
    private int port;
    @Value("${spring.redis3.password:redis123}")
    private String password;
    @Value("${spring.redis3.timeout:6000}")
    private int timeout;
    @Value("${spring.redis3.database:0}")
    private int database;

    //pool映射
    @Value("${spring.redis3.lettuce.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis3.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis3.lettuce.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis3.lettuce.pool.max-wait}")
    private long maxWait;

    //setinel映射
    List<String> nodes;

    @Value("${spring.redis3.sentinel.master:mymaster}")
    private String master;

    @Bean
    public GenericObjectPoolConfig redisPool3() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMinIdle(minIdle);
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(maxWait);
        return config;
    }

    @Bean
    public RedisSentinelConfiguration redisConfig3() {//哨兵的节点要写代码组装到配置对象中
        RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
        redisConfig.sentinel(host, port);
        redisConfig.setMaster(master);
        redisConfig.setPassword(RedisPassword.of(password));
        if (nodes != null) {
            List<RedisNode> sentinelNode = new ArrayList<>();
            for (String sen : nodes) {
                String[] arr = sen.split(":");
                sentinelNode.add(new RedisNode(arr[0], Integer.parseInt(arr[1])));
            }
            redisConfig.setSentinels(sentinelNode);
        }
        return redisConfig;
    }

    @Bean("factory3")
    public LettuceConnectionFactory factory3(@Qualifier("redisPool3") GenericObjectPoolConfig config,
                                             @Qualifier("redisConfig3") RedisSentinelConfiguration redisConfig) {//注意传入的对象名和类型RedisSentinelConfiguration
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
        return new LettuceConnectionFactory(redisConfig, clientConfiguration);
    }

    /**
     * 哨兵redis数据源
     *
     * @param connectionFactory
     * @return
     */
    @Bean("redisTemplate3")
    public RedisTemplate<String, Object> redisTemplate3(@Qualifier("factory3") LettuceConnectionFactory connectionFactory) {//注意传入的对象名
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
