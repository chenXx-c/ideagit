package com.example.springboot.service;

import com.example.springboot.controller.dto.UserDto;
import com.example.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhichao
 * @since 2024-01-05
 */
public interface IUserService extends IService<User> {

    UserDto login(UserDto userDto);

    UserDto register(UserDto userDto);
}
