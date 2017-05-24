package com.zk.sbt.resource.apiv1.developer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zk_chs on 4/21/17.
 */
@ApiModel("添加ap的model")
public class DeveloperRep {

    @ApiModelProperty(value = "就是id")
    private Integer id;
    @ApiModelProperty(value = "开发者名称", dataType = "String")
    private String name;
    private Integer age;
    private String email;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
