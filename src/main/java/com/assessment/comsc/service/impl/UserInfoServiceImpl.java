package com.assessment.comsc.service.impl;


import com.assessment.comsc.entity.UserInfo;
import com.assessment.comsc.mapper.UserInfoMapper;
import com.assessment.comsc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    public UserInfo getUserInfoByMail(String mail) {

        return userInfoMapper.queryByEmail(mail);
    }
}
