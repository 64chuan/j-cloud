package org.example.service;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 重试框架
 * 1.需要引入依赖包 spring-retry、spring-boot-starter-aop
 * 2.启动类添加注解 @EnableRetry
 * 4.方法添加注解@Retryable
 * 5.添加回调方法，方法需要添加注解 @Recover
 */
@Service
public class UserRetryService {

    /**
     * 重试框架
     *
     * 如果是单线程，会造成堵塞；需要考虑使用场景
     * value  默认无，即所以异常都会重试；有值后，只有存在的异常才会重试，达到重试次数最大次数时，调用回调接口
     * maxAttempts 重试最大链接次数，默认为3次（饱含第一次）
     * backoff  delay 间隔秒数，multiplier 间隔倍率；   即每次调用，与上一次调用间隔时间为   delay * (n次-1) * multiplier
     *
     * @throws Exception
     */
    @Retryable(value = {IOException.class,RemoteAccessException.class},maxAttempts = 4,backoff = @Backoff(delay = 1000,multiplier = 2))
    public void call(long t) throws Exception{
        long s = System.currentTimeMillis() - t;
        System.out.println(s +  "   do  something ...   ");

        /*
        10   do  something ...
        1016   do  something ...
        3026   do  something ...
        7036   do  something ...
        Recover:RPC调用失败...
         */
        throw new RemoteAccessException("RPC调用失败...");
    }

    /**
     * 存在异常就会回调
     * @param e 与此参数类型匹配的异常，将直接捕获并在此方法处理，不会再抛出到外层
     */
    @Recover
    public void recover(RemoteAccessException e){
        // 添加日志记录？
        System.out.println("Recover:" + e.getMessage());
    }
}
