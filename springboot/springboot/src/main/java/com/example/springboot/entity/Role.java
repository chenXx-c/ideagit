package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String phone;
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String address;
    //表名和数据库名字不一样
    // @TableField(value = "avatar_url")
    private String avatar;
    private String description;
    private Date createTime;



}
