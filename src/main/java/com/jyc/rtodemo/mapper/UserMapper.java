package com.jyc.rtodemo.mapper;

import com.jyc.rtodemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectUserByJdpin(long jdpin);


}
