package org.example.listener;

import org.example.dto.UserMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class UserMsgListener {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(Sink.INPUT)
    public void onUserMsgSink(UserMsg userMsg){
        this.logger.info("receive user msg: {}", userMsg);
        System.out.println("---------------------"+ userMsg.toString()+"--------------------------");
    }
}
