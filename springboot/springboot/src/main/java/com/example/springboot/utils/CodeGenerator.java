package com.example.springboot.utils;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

//代码生成器
public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/czc"    // url
                        , "root"        // username
                        , "123456"      // password
                )
                .globalConfig(builder -> {
                    builder.author("chenzhichao") // 设置作者
//                        .enableSwagger() // 开启 swagger 模式
//                        .fileOverride() // 覆盖已生成文件 (已被弃用)
                            .outputDir("F:\\vue\\vue\\springboot\\springboot\\src\\main\\java\\"); // 指定输出目录
                //F:\vue\vue\springboot\springboot\src\main\java
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.springboot") // 设置父包名
                            .moduleName(null) // 设置父包模块名 (可以为空串 "")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\vue\\springboot\\springboot\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder .controllerBuilder().enableHyphenStyle()//开启驼峰转连字符
                                    .enableRestStyle();//开启生成RestController控制器
                    builder .addInclude("sys_menu")// 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀


                })
//            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
