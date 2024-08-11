package com.assessment.comsc.service;


import com.assessment.comsc.entity.UserInfo;

public interface UserInfoService {

    /**
     *
     * @param mail just input the mail String
     * @return account detail.
     */
    UserInfo getUserInfoByMail(String mail);

}
