package org.example.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout:0}")
    private int timeout;

    @Value("${spring.redis.pool.max-active:100}")
    private int maxActive;

    @Value("${spring.redis.pool.max-idle:20}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait-millis:15000}")
    private long maxWait;

    @Bean
    public JedisConnectionFactory redisConnectionFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);

        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        if(null != this.password && this.password.length() > 0){
            factory.setPassword(this.password);
        }
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        factory.setPoolConfig(poolConfig);
        return factory;
    }

    protected RedisTemplate buildRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 序列化处理
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 序列化处理
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean("userRedisTemplate")
    protected RedisTemplate userRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        return this.buildRedisTemplate(redisConnectionFactory);
    }
}
