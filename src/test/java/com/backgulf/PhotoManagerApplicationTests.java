package com.backgulf;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class PhotoManagerApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        Cookie cookie = new Cookie("a","b");
    }

    @Test
    public void  test(){
        String sql = "select name from sqlite_master where type='table'";
        List<String> tables = jdbcTemplate.queryForList(sql,String.class);

        if (!tables.contains("sys_dict")){
            createTable2SysDict();
        }
        if (!tables.contains("file_entity")){
            createTable2File();
        }
    }

    private void initDB(){
        String createTable = "create table files (id int,)";
    }


    private void createTable2File(){
        String sql = "CREATE TABLE \"file_entity\" (\"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\"name\" TEXT,\"md5\" TEXT,\"size\" TEXT,\"localRelPath\" TEXT,\"remoteRelPath\" TEXT,\"type\" TEXT)\n";
        jdbcTemplate.execute(sql);
    }
    private void createTable2SysDict(){
        String sql = "create table sys_dict ('key' text,'value' text,'remark' text)";
        jdbcTemplate.execute(sql);
        String initSQL = "replace into sys_dict values ('user','admin','adminadmin'),('inited',1,'')";
        jdbcTemplate.execute(initSQL);
    }
}
