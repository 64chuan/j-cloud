package org.example.utils;

import org.example.dto.UserMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.cloud.stream.messaging.Source;

/**
 * 消息发送组件
 * 使用@Component声明一个组件，使用时可以用@Autowire直接注入
 */
@Component
public class UserMsgSender {
    protected Logger logger = LoggerFactory.getLogger(UserMsgSender.class);
    private Source source;

    @Autowired
    public UserMsgSender(Source source){
        this.source = source;
    }

    /**
     * 消息发送
     * @param userMsg 用户信息
     */
    public void sendMsg(UserMsg userMsg){
        this.logger.debug("发送用户消息:{}",userMsg);
        System.out.println("发送用户消息:{}" + userMsg.toString());
        this.source.output().send(MessageBuilder.withPayload(userMsg).build());
        System.out.println("发送用户消息:{} end" + userMsg.toString());
    }
}
