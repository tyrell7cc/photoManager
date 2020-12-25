package com.backgulf.system.secure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //个人用 所以仅允许一人登录就行了
    private User user;


    public User getUserFromRequest(HttpServletRequest request){
        if (user==null){
            return null;
        }
        Cookie[] ks = request.getCookies();
        if (ks==null){
            ks = new Cookie[0];
        }
        for (Cookie cookie:ks){
            if (cookie.getName().equals("2log")){
                if (cookie.getValue().equals(user.getCookie().getValue())){
                    return user;
                }
            }
        }
        return  null;
    }

    public boolean whetherLogged(HttpServletRequest request){
        return getUserFromRequest(request)!=null;
    }

    public User login(HttpServletRequest request, HttpServletResponse response, String username, String password){
        if (whetherLogged(request)){
            return user;
        }
        String sql = "select value,remark from sys_dict";
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        if (result.size()<1){
            return null;
        }
        String name = result.get(0).get("value")+"";
        String pwd = result.get(0).get("remark")+"";
        if (!name.equals(username)||!pwd.equals(password)){
            return null;
        }
        this.user = new User(name,password);
        String value = user.hashCode()+ LocalDateTime.now().toString();
        Cookie cookie = new Cookie("2log", Base64.getEncoder().encodeToString(value.getBytes()));
        cookie.setMaxAge(60*60*2);
        user.setCookie(cookie);
        response.addCookie(cookie);
        return user;
    }
}
