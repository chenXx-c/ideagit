package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDto login(UserDto userDto) {

        User one = getUserInfo(userDto);

        if (one != null) {

            BeanUtil.copyProperties(one, userDto, true);
//        设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());

            userDto.setToken(token);
            return userDto;
        } else {
            throw new ServiceException(Constants.CODE_500, "用户名或密码错误");
        }


    }

    @Override
    public UserDto register(UserDto userDto) {

        User one = getUserInfo(userDto);
        if(one==null){
            one=new User();
            BeanUtil.copyProperties(userDto,one,true);
            save(one);

        }else{
            throw new ServiceException(Constants.CODE_500, "用户名已存在");
        }

        return null;
    }
    public User getUserInfo(UserDto userDto){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDto.getUsername());
        queryWrapper.eq("password", userDto.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);


        } catch (Exception e) {
//              LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");


        }
       return one;

    }
}
