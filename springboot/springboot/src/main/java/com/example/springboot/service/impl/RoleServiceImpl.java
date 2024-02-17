package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.RoleDto;
import com.example.springboot.entity.Role;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.RoleMapper;
import com.example.springboot.service.RoleService;
import com.example.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenzhichao
 * @since 2024-01-05
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public RoleDto login(RoleDto roleDto) {

        Role one = getUserInfo(roleDto);

        if (one != null) {

            BeanUtil.copyProperties(one, roleDto, true);
//        设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());

            roleDto.setToken(token);
            return roleDto;
        } else {
            throw new ServiceException(Constants.CODE_500, "用户名或密码错误");
        }


    }

    @Override
    public RoleDto register(RoleDto roleDto) {

        Role one = getUserInfo(roleDto);
        if(one==null){
            one=new Role();
            BeanUtil.copyProperties(roleDto,one,true);
            save(one);

        }else{
            throw new ServiceException(Constants.CODE_500, "用户名已存在");
        }

        return null;
    }
    public Role getUserInfo(RoleDto roleDto){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", roleDto.getUsername());
        queryWrapper.eq("password", roleDto.getPassword());
        Role one;
        try {
            one = getOne(queryWrapper);


        } catch (Exception e) {
//              LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");


        }
       return one;

    }

}
