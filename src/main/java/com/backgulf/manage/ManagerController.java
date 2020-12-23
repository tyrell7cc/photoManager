package com.backgulf.manage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    @GetMapping("")
    public String main(){
        return "manager.html";
    }

}
