package com.jyc.rtodemo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceMapper {

    Long selectByDeviceId(String did);
}
