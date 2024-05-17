package org.example.feign;

import org.example.dto.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 远程连接，需要在启动类扫描到此类，并注入Bean：
 *      @EnableFeignClients(basePackages = "com.example.*") //开启feign
 */
@FeignClient("PROVIDER-USER")
public interface IUserRemoteClient {
    @RequestMapping(value="/user/loadUser/{id}",method = RequestMethod.GET)
    UserDto load(@PathVariable("id") Long id);
}
