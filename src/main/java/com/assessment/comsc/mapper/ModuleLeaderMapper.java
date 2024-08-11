package com.assessment.comsc.mapper;

import com.assessment.comsc.entity.ModuleLeaderForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModuleLeaderMapper {


    void insert(ModuleLeaderForm form);

    void update(ModuleLeaderForm form);
}
