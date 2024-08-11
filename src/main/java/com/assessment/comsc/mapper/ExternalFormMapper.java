package com.assessment.comsc.mapper;

import com.assessment.comsc.entity.ExternalForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExternalFormMapper {


    void insert(ExternalForm externalForm);


    ExternalForm selectExternalFormById(Integer assessmentId);

}
