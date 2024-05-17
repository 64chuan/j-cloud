package org.example.service.impl;

import org.example.dto.UserDto;
import org.example.dto.UserMsg;
import org.example.feign.IUserRemoteClient;
import org.example.redis.UserRedisRepository;
import org.example.service.UserService;
import org.example.utils.UserMsgSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    // feign 远程访问微服务调用
    @Autowired
    protected IUserRemoteClient userRemoteClient;

    // redis
    @Autowired
    protected UserRedisRepository userRedisRepository;

    @Autowired
    protected UserMsgSender userMsgSender;

    @Autowired
    protected Tracer tracer;

    @Override
    public UserDto load(Long id){
        // 先从redis获取
        UserDto userDto = this.userRedisRepository.findOne(id);
        // redis缓存存在用户信息，直接返回
        if(null != userDto){
            logger.debug("  已从Redis缓存中读取到用户：{}的信息 ", id);
            return userDto;
        }
        logger.debug("  Redis缓存中不存在用户：{}的信息，尝试从远程进行加载 ", id);
        // redis缓存不存在用户信息，从远程库获取
        userDto = userRemoteClient.load(id);
        // 远程库存在用户信息，缓存到redis
        if(null != userDto) {
            this.userRedisRepository.saveUser(userDto);
        }
        // 返回用户信息
        return userDto;
    }

    @Override
    public UserDto save(UserDto userDto){
        if(null != userDto.getId()){
            UserDto user = this.userRedisRepository.findOne(userDto.getId());
            if(null == user){
                // 新增
                userDto.setId(1234L);
            } else {
                // 修改
                // userDto.setId(2234L);
            }
        } else {
            // 修改
            // userDto.setId(2234L);
        }
        this.sendMsg(UserMsg.UA_UPDATE,userDto.getId());
        return userDto;
    }

    @Override
    public void delete(Long id){
        this.userRedisRepository.delete(id);
        // 发送删除信息
        sendMsg(UserMsg.UA_DELETE,id);
    }

    /**
     * 发送消息
     * @param action
     * @param userId
     */
    private void sendMsg(String action,Long userId){
        this.userMsgSender.sendMsg(new UserMsg(action, userId, getTracerId()));
    }

    /**
     * 获取跟踪ID
     * @return 跟踪ID
     */
    private String getTracerId(){
        return this.tracer.getCurrentSpan().traceIdString();
    }
}
