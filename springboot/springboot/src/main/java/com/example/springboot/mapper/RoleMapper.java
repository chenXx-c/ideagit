package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Role;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {
//
//    @Select("select *from sys_user")
//    List<User>findAll();
//
////    @Insert("insert into sys_user(username,password,nickname,email,phone,address) values #{username},#{Password}" +
////            "#{nickname},#{email},#{phone},{address};")
//@Insert("insert into sys_user(username, password, nickname, email, phone, address) values " +
//        "(#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
//     int insert(User user);
//
//
//
//    int update(User user);
//
//    @Delete("delete from sys_user where id=#{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from sys_user where username like concat('%',#{username},'%') limit #{pageNum},#{pageSize}")
//   List<User> selectPage(Integer pageNum, Integer pageSize,String username);
//
//
//    @Select("select count(*) from sys_user  where username like concat('%', #{username} , '%') ")
//    Integer selectTotal(String username);

}