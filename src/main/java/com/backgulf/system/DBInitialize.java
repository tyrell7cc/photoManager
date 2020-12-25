package com.backgulf.system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInitialize implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
            this.DBInit();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void DBInit(){
        String sql = "select name from sqlite_master where type='table'";
        List<String> tables = jdbcTemplate.queryForList(sql,String.class);

        if (!tables.contains("sys_dict")){
            createTable2SysDict();
        }
        if (!tables.contains("file_entity")){
            createTable2File();
        }
        System.out.println("数据库初始化完成...");
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

/*
CREATE TABLE "file_entity" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "name" TEXT,
  "md5" TEXT,
  "size" TEXT,
  "localRelPath" TEXT,
  "remoteRelPath" TEXT,
  "type" TEXT
)
 */