package com.example.springboot.controller.dto;


import lombok.Data;

//接受前端
@Data

public class RoleDto {

       private String username;
       private String password;
       private String nickname;
       private String avatar;
       private String token;


}
