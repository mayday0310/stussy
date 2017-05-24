package com.zk.sbt.mybatis.dao.mapper;

import com.zk.sbt.entity.Developer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zk_chs on 4/22/17.
 */
@Mapper
public interface DeveloperMapper {

    @Select("SELECT id, developer_name name, create_at createAt FROM developer WHERE id = #{developerId}")
    Developer findById(Long developerId);

    @Select("SELECT id, developer_name name, create_at createAt FROM developer WHERE developer_name = #{developerName}")
    Developer findByName(String developerName);

}
