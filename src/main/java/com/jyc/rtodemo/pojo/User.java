package com.jyc.rtodemo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private Date registerDate;
    private Date lastLoginDate;

}
