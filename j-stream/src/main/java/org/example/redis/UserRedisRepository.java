package org.example.redis;

import org.example.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class UserRedisRepository {
    public static final String USER_KEY_PRE = "user:";

    @Autowired
    @Qualifier("userRedisTemplate")
    private RedisTemplate<String, UserDto> userRedisTemplate;

    private ValueOperations<String,UserDto> operations;

    @PostConstruct
    public void init(){
        this.operations = this.userRedisTemplate.opsForValue();
    }

    public void saveUser(UserDto userDto){
        this.operations.set(this.buildKey(userDto.getId()),userDto);
    }

    public UserDto findOne(long userId){
        String key = buildKey(userId);
        if(!this.userRedisTemplate.hasKey(key)){
            return null;
        }
       return this.operations.get(key);
    }

    public void delete(long userId){
        this.userRedisTemplate.delete(this.buildKey(userId));
    }

    protected String buildKey(Long userId){
        return USER_KEY_PRE + String.valueOf(userId);
    }
}
