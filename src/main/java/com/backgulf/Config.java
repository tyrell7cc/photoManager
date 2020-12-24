package com.backgulf;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    public static String GITEE_OWNER;
    public static String GITEE_REPO;
    public static String GITEE_TOKEN;
    public static String GITEE_ROMOTEPATH;
    public static String GITEE_LOCALPATH;


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

    @Value("${gitee.local_path}")
    public void setGiteeLocalpath(String a){
        GITEE_LOCALPATH = a;
    }

    @Value("${gitee.remote_path}")
    public void setGiteeRomotepath(String s){
        GITEE_ROMOTEPATH = s;
    }
}
