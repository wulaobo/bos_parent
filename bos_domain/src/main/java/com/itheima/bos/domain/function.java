package com.itheima.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//权限实体  auth_function
public class function implements Serializable {
    private String id;  //编号
    private String name;  //名称
    private function parentFunction; //权限上一级
    private String code;  //关键字
    private String description;  //描述
    private String page;  //路径
    private String generatemenu;  //是否生成菜单 1：是 0：否
    private Integer zindex;  //优先级
    //private String pid;  父权限编号    外键

    //权限与角色的多对多关系
    private Set<Role> roles_fun = new HashSet<Role>();
    //权限的下一级
    private Set<function> sonFunctions = new HashSet<function>();

    public String getpId(){
        if(parentFunction==null){
            return "0";
        }else{
            return parentFunction.getId();
        }
    }

    public Set<Role> getRoles_fun() {

        return roles_fun;
    }

    public void setRoles_fun(Set<Role> roles_fun) {
        this.roles_fun = roles_fun;
    }

    public Integer getZindex() {

        return zindex;
    }

    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }

    public String getGeneratemenu() {

        return generatemenu;
    }

    public void setGeneratemenu(String generatemenu) {
        this.generatemenu = generatemenu;
    }

    public String getPage() {

        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

    public function getParentFunction() {
        return parentFunction;
    }

    public void setParentFunction(function parentFunction) {
        this.parentFunction = parentFunction;
    }

    public Set<function> getSonFunctions() {
        return sonFunctions;
    }

    public void setSonFunctions(Set<function> sonFunctions) {
        this.sonFunctions = sonFunctions;
    }

    @Override
    public String toString() {
        return "function{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", page='" + page + '\'' +
                ", generatemenu='" + generatemenu + '\'' +
                ", zindex=" + zindex +
                '}';
    }
}
