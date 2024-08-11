package com.assessment.comsc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * User Table Mapping Object
 */
@Data
public class UserInfo implements Serializable {

    private Long id;

    private Long userId;

    private String mail;

    private String password;

    private String userName;

    private Integer role;

    private String url;


}

