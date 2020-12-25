package com.backgulf.files.controller;


import com.backgulf.files.entity.FileEntity;
import com.backgulf.files.entity.ResultUtil;
import com.backgulf.files.service.FileService;
import com.backgulf.gitee.GiteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class ManagerController {
    @Autowired
    private FileService fileService;
    @Autowired
    private GiteeService giteeService;

    @GetMapping("")
    public String main(){
        return "manager.html";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @PostMapping("/api/add/gitee")
    @ResponseBody
    public Map giteeAdd(@RequestParam("file") MultipartFile file){
        if (file==null||file.isEmpty()){
            return ResultUtil.fail("文件不存在");
        }
        String fileName = file.getOriginalFilename();
        if (fileName==null||fileName.equals("")){
            return ResultUtil.fail("拿不到文件名，fuck off");
        }
        String md5 = null;
        try {
            md5 = DigestUtils.md5DigestAsHex(file.getBytes());
        }catch (Exception e){
            return ResultUtil.fail("计算md5失败 fuck u");
        }

        FileEntity entity = fileService.ofMd5(md5);
        if (entity!=null){
            return ResultUtil.success("",entity.getRemoteRelPath());
        }
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setMd5(md5);
        fileEntity.setType("gitee.com");
        fileEntity.setSize(file.getSize()+"");

        String path = giteeService.add(fileEntity,file);
        return ResultUtil.success("",path);
    }


    @GetMapping("/api/all")
    @ResponseBody
    public Map getAll(){
        List<FileEntity> entities = fileService.getAll();
        return ResultUtil.success("",entities);
    }
}
