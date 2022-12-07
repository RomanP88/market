package ru.gb.market.cart.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        return new JedisConnectionFactory(); //фабрика соединений для подключения к Redis
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();//создаем бин RedisTemplate
        template.setKeySerializer(new StringRedisSerializer());//сериализатор для ключа
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());//сериализатор для объекта (JSON)
        template.setConnectionFactory(jedisConnectionFactory());//устанавливаем фабрику для соединения
        return template;
    }
}
