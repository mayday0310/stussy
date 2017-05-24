package com.zk.google.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qcon on 2017/3/21.
 */
public class GsonUser {

    private String name;
    private int age;
    /** 当要解析的json出现多个属性时，以最后一个出现的值为准 */
    @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
    private String emailAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
