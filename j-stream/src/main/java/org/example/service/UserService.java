package org.example.service;

import org.example.dto.UserDto;

public interface UserService {
    /**
     * 查询用户信息
     * @param id 主键
     * @return UserDto
     */
    public UserDto load(Long id);

    /**
     * 保存
     * @param userDto 用户信息
     * @return UserDto
     */
    public UserDto save(UserDto userDto);

    /**
     * 删除用户信息
     * @param id id
     */
    public void delete(Long id);
}
