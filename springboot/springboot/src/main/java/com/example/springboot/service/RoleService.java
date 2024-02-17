package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.RoleDto;
import com.example.springboot.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhichao
 * @since 2024-01-05
 */
public interface RoleService extends IService<Role> {

    RoleDto login(RoleDto roleDto);



    RoleDto register(RoleDto roleDto);
}
