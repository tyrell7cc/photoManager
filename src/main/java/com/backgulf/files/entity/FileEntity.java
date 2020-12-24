package com.backgulf.files.entity;

public class FileEntity {
    private Integer id;
    private String name;
    private String md5;
    private String size;

    private String type;
    private String localRelPath;//本地相对路径
    private String remoteRelPath;//远程相对路径


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLocalRelPath() {
        return localRelPath;
    }

    public void setLocalRelPath(String localRelPath) {
        this.localRelPath = localRelPath;
    }


    public String getRemoteRelPath() {
        return remoteRelPath;
    }

    public void setRemoteRelPath(String remoteRelPath) {
        this.remoteRelPath = remoteRelPath;
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
