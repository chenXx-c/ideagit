package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.springboot.service.IMenuService;
import com.example.springboot.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenzhichao
 * @since 2024-02-07
 */
@RestController
@RequestMapping("/menu")
    public class MenuController {

@Autowired
private IMenuService menuService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Menu menu){
        return Result.success(menuService.saveOrUpdate(menu));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id){
        return Result.success(menuService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(menuService.removeByIds(ids));
        }


@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id){
        return Result.success(menuService.getById(id));
        }


@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {

        IPage<Menu> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(page, queryWrapper));


        }


@GetMapping("/username/{username}")
public Result<Menu> findOne(@PathVariable String username){
        QueryWrapper<Menu>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(menuService.getOne(queryWrapper));
        }


        }


