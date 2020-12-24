package com.backgulf.gitee;


import com.backgulf.Config;
import com.backgulf.files.entity.FileEntity;
import com.backgulf.files.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class GiteeService {
    Logger logger = LoggerFactory.getLogger(GiteeService.class);
    @Autowired
    private FileService fileService;

    public String add(FileEntity fileEntity, MultipartFile multipartFile){
        String path = LocalDate.now().toString()+"/"+fileEntity.getName();
        fileEntity.setRemoteRelPath(path);
        fileEntity.setLocalRelPath(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        try {
            body.add("content", Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        }catch (Exception e){
            logger.error("获取文件字节码出错");
            return null;
        }
        body.add("access_token", Config.GITEE_TOKEN);
        body.add("message","fuck");
        String url = "https://gitee.com/api/v5/repos/"+Config.GITEE_OWNER+"/"+Config.GITEE_REPO+"/contents/"+path;
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(body,headers);
        try {
            ResponseEntity<String> s = new RestTemplate().exchange(url, HttpMethod.POST,entity,String.class);
            logger.info(s.toString());
            fileService.addOne(fileEntity);
        }catch (Exception e){
            logger.error("上传文件出错,将不再尝试保存文件",e);
            return null;
        }
        return path;
    }


    public String delRemoteFile(String relPath){
        return "";
    }



}
