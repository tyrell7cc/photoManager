package com.backgulf.system.secure;


import com.backgulf.files.entity.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("hello")
    public Map login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (user.getUsername().equals("")||user.getPassword().equals("")){
            return ResultUtil.fail("请输入密码,ok?");
        }
        User user1 = userService.login(request,response,user.getUsername(),user.getPassword());
        if (user1==null){
            return ResultUtil.fail("请输入正确的账号密码");
        }
        response.sendRedirect("/");
        return ResultUtil.success();
    }
}
