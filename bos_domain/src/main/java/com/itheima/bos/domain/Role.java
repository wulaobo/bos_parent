package com.itheima.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//角色实体  auth_role
public class Role implements Serializable {
     private String id;  //角色编号
     private String name;  //角色名称
     private String code;  //关键字
     private String description;  //角色描述

    //角色与用户多对多
    private Set<User> users = new HashSet<User>();

    //角色与权限多对多
    private Set<function> functions = new HashSet<function>();

    public Set<function> getFunctions() {

        return functions;
    }

    public void setFunctions(Set<function> functions) {
        this.functions = functions;
    }

    public Set<User> getUsers() {

        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }
}
