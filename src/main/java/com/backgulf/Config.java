package com.backgulf;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    public static String GITEE_OWNER;
    public static String GITEE_REPO;
    public static String GITEE_TOKEN;


    @Value("${gitee.owner}")
    public void setGiteeOwner(String value){
        GITEE_OWNER = value;
    }

    @Value("${gitee.repo}")
    public void setGiteeRepo(String value){
        GITEE_REPO = value;
    }

    @Value("${gitee.access_token}")
    public void setGiteeToken(String value){
        GITEE_TOKEN = value;
    }
}
