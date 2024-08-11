package com.assessment.comsc.mapper;


import com.assessment.comsc.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Table database access layer
 * @since 2023-11-25 20:57:34
 */
@Mapper
public interface UserInfoMapper {

    UserInfo queryByEmail(String mail);


}

