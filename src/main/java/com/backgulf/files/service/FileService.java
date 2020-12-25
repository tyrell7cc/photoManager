package com.backgulf.files.service;

import com.backgulf.system.Config;
import com.backgulf.files.entity.FileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileService {
    Logger logger = LoggerFactory.getLogger(FileService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public FileEntity ofMd5(String md5){
        String sql = "select * from file_entity where md5 = ?";
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql,md5);
        if (result.size()<1){
            return null;
        }
        FileEntity entity = new FileEntity();
        for (Map<String,Object> data:result){
            entity.setId((Integer) data.get("id"));
            entity.setName(data.get("name")+"");
            entity.setMd5(md5);
            entity.setSize(data.get("size")+"");
            entity.setLocalRelPath(data.get("localRelPath")+"");
            entity.setRemoteRelPath(data.get("remoteRelPath")+"");
        }
        return entity;
    }

    public boolean addOne(FileEntity fileEntity){
        String sql = "insert into file_entity (name,md5,type,size,localRelPath,remoteRelPath) values (?,?,?,?,?,?)";
        int i = jdbcTemplate.update(sql,fileEntity.getName(),fileEntity.getMd5(),fileEntity.getType(),fileEntity.getSize(),fileEntity.getLocalRelPath(),fileEntity.getRemoteRelPath());
        logger.warn("添加:"+fileEntity.getName()+"   "+i);
        return true;
    }

    public List<FileEntity> getAll(){
        String sql = "select * from file_entity";
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        List<FileEntity> entities = new ArrayList<>();
        for (Map<String,Object> data:result){
            FileEntity entity = new FileEntity();
            entity.setId((Integer) data.get("id"));
            entity.setName(data.get("name")+"");
            entity.setType(data.get("type")+"");
            entity.setMd5(data.get("md5")+"");
            entity.setSize(data.get("size")+"");
            entity.setLocalRelPath(data.get("localRelPath")+"");
            entity.setRemoteRelPath(pathOfType(entity.getType(),data.get("remoteRelPath")+""));
            entities.add(entity);
        }
        return entities;
    }

    private String pathOfType(String type,String relPath){
        if (type.equals("gitee.com")){
            return Config.GITEE_ROMOTEPATH+"/"+relPath;
        }
        return null;
    }
}
