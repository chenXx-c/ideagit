package com.example.springboot.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.RoleDto;
import com.example.springboot.entity.Role;
import com.example.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/role")


public class RoleController {


    @Autowired
    private RoleService roleService;

    @PostMapping
//    public boolean save(@RequestBody User user) {
    public Result save(@RequestBody Role role) {
//新增更新
        return Result.success(roleService.saveOrUpdate(role));

    }
    @GetMapping
    public Result findAll(){
        return Result.success(roleService.list());
    }


    @PostMapping("/register")
    public Result register(@RequestBody RoleDto roleDto) {

        String username = roleDto.getUsername();
        String password = roleDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.erro(Constants.CODE_400, "参数错误");
        }

        RoleDto dto = roleService.register(roleDto);
        return Result.success(roleDto);

    }

    @PostMapping("/login")
    public Result login(@RequestBody RoleDto roleDto) {

        String username = roleDto.getUsername();
        String password = roleDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.erro(Constants.CODE_400, "参数错误");
        }

        RoleDto dto = roleService.login(roleDto);
        return Result.success(roleDto);

    }


    //查询所有
//    @GetMapping
//    public List<User> index() {
//
//        return userService.list();
//    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        return Result.success(roleService.removeById(id));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteBath(@RequestBody List<Integer> ids) {

        return Result.success(roleService.removeByIds(ids));
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {

        return Result.success(roleService.getById(id));
    }
//    @GetMapping("/page")
//    public Map<String,Object> finPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,
//                                      @RequestParam String username
//
//                                      ) {
//
//        pageNum=  ( pageNum-1)* pageSize;
//        Integer total = userMapper.selectTotal(username);
//        List<User> data = userMapper.selectPage(pageNum, pageSize,username);
//        Map<String,Object> res=new HashMap<>();
//
//        res.put("data",data);
//        res.put("total",total);
//        return   res;


    // 分页查询mybatis-plus
    @GetMapping("/page")
//    public IPage<User> findPage(@RequestParam Integer pageNum,
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address
    ) {

        IPage<Role> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(username)) {

            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);

        }
        if (!"".equals(address)) {

            queryWrapper.like("address", address);
        }
        //获取当前用户信息
//        User currentAdmin = TokenUtils.getCurrentAdmin();
//        System.out.println(currentAdmin.getNickname());
        return Result.success(roleService.page(page, queryWrapper));

    }

    @GetMapping("/username/{username}")
    public Result<Role> findOne(@PathVariable String username){
        QueryWrapper<Role>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(roleService.getOne(queryWrapper));
    }


}
