package com.jyc.rtodemo.service;

import com.jyc.rtodemo.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    public Long findJdpinByDeviceId(String did){
        return deviceMapper.selectByDeviceId(did);
    }
}
