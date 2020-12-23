package com.backgulf.gitee;


import com.backgulf.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@RequestMapping("gitee")
public class GiteeController {
    Logger logger = LoggerFactory.getLogger(GiteeController.class);


    @GetMapping("")
    public String test(){
        return Config.GITEE_TOKEN;
    }

    @PostMapping("")
    public void add(@RequestParam("file") MultipartFile file){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String,String> body = new LinkedMultiValueMap<>();

            body.add("content", Base64.getEncoder().encodeToString(file.getBytes()));
            body.add("access_token",Config.GITEE_TOKEN);
            body.add("message","fuck");

            String path = "cukl";
            String url = "https://gitee.com/api/v5/repos/"+Config.GITEE_OWNER+"/"+Config.GITEE_REPO+"/contents/"+path;
            HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(body,headers);

            ResponseEntity<String> s = new RestTemplate().exchange(url, HttpMethod.POST,entity,String.class);
            System.out.println(s);
        }catch (Exception e){
            logger.error("",e);
        }
    }
}
