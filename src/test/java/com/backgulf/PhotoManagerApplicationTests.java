package com.backgulf;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SpringBootTest
public class PhotoManagerApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void  test(){
        System.out.println("fuickl");
        String s = jdbcTemplate.queryForObject("select id from user",String.class);
        System.out.println(s);
    }

    private void initDB(){
        String createTable = "create table files (id int,)";
    }
}
