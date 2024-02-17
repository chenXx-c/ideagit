package com.example.springboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Files;
import com.example.springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private FileMapper fileMapper;

    //
//    @GetMapping("findAll")
//    public Result findAll(){
//
//        return Result.success(fileMapper.selectList(null));
//    }
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String type = FileUtil.extName(originalFileName);
        long size = file.getSize();

        //定义一个文件一个标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUID);
        //判断文件目录是否存在，不存在创建
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String url;
        //获取的文件存储到磁盘目录
        file.transferTo(uploadFile);

        //获取的文件的md5,避免上传相同的文件
        String md5 = SecureUtil.md5(uploadFile);
        //查询文件md5
//        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("md5",md5);
//        Files files = fileMapper.selectOne(queryWrapper);
        Files dpFiles = getFileByMd5(md5);
        if (dpFiles != null) {
            url = dpFiles.getUrl();
            uploadFile.delete();
        } else {

            url = "http://localhost:8081/file/" + fileUUID;

        }
        //存储在数据库

        Files saveFile = new Files();
        saveFile.setName(originalFileName);
        saveFile.setType(type);
        saveFile.setSize(size / 1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
        return url;

    }

    public Files getFileByMd5(String md5) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        return fileMapper.selectOne(queryWrapper);

    }

    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {


        File uploadFile = new File(fileUploadPath + fileUUID);
        ServletOutputStream os = response.getOutputStream();

        response.addHeader("Contene-Disposition", "attachment;filename" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();

    }


    @GetMapping("/page")
//    public IPage<User> findPage(@RequestParam Integer pageNum,
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name

    ) {


        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        //查询未删除的记录
        queryWrapper.eq("is_delete", false);
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {

            queryWrapper.like("name", name);
        }

        return Result.success(fileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Files files = fileMapper.selectById(id);
        files.setDelete(true);
        fileMapper.updateById(files);
        return Result.success();
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteBath(@RequestBody List<Integer> ids) {

        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for (Files file : files) {
            file.setDelete(true);
            fileMapper.updateById(file);
        }


        return Result.success();
    }

//批量删除
//@PostMapping("/del/batch")
//public Result deleteBath(@RequestBody List<Integer> ids) {
//
//    return Result.success(fileMapper.deleteBatchIds(ids));
//}


    @PostMapping("/update")
//    public boolean save(@RequestBody User user) {
    public Result save(@RequestBody Files files) {
//新增更新
        return Result.success(fileMapper.updateById(files));

    }


}
