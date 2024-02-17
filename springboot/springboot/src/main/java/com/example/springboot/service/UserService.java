//package com.example.springboot.service;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.example.springboot.controller.dto.UserDto;
//import com.example.springboot.entity.User;
//import com.example.springboot.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService extends ServiceImpl<UserMapper,User>  {
//
//
//    public boolean saveUser(User user){
//
////        if(user.getId()!=null){
////          return  save(user);
////        }else{
////         return updateById(user);
////        }
//       return saveOrUpdate(user);
//
//    }
//
//    public boolean login(UserDto userDto) {
//        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("username",userDto.getUsername());
//        queryWrapper.eq("password",userDto.getPassword());
//        User one=getOne(queryWrapper);
//        return one!=null;
//    }
//
//
////    @Autowired
////    private UserMapper userMapper;
//
//
////    public int save1(User user){
////
////        if(user.getId()==null){
////          return  userMapper.insert(user);
////        }else{
////          return  userMapper.update(user);
////        }
////
////    }
//
//
//
//}
