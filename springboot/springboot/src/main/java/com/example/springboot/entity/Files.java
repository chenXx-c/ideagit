package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_file")
public class Files {
     @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private String md5;
    private Long size;
    private String url;
    private boolean isDelete;
    private boolean enable;


}
