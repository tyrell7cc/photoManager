package com.backgulf.files.entity;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

    public static Map result(boolean isok,String message,Object content){
        Map data = new HashMap();
        data.put("isok",isok);
        data.put("message",message);
        data.put("content",content);
        return data;
    }

    public static Map success(String message){
        return result(true,message,null);
    }
    public static Map success(){
        return result(true,"",null);
    }
    public static Map success(String message,Object data){
        return result(true,message,data);
    }

    public static Map fail(String message){
        return result(false,message,null);
    }
    public static Map fail(){
        return result(false,"",null);
    }
}
