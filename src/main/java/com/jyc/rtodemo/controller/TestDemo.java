package com.jyc.rtodemo.controller;

import com.jyc.rtodemo.dto.*;
import com.jyc.rtodemo.pojo.User;
import com.jyc.rtodemo.service.DeviceService;
import com.jyc.rtodemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@Slf4j
public class TestDemo {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public Resp test(@RequestBody AD ad){


        String id = ad.getId();
        //设备id
        String did = ad.getDid();
        log.info("did={}", did);
        Long jdpin = deviceService.findJdpinByDeviceId(did);

        User user = userService.findUserByJdpin(jdpin);
        log.info("user={}", user);
        Date lastLoginDate = user.getLastLoginDate();
        Date nowDate = new Date();
        int days = (int) ((nowDate.getTime() - lastLoginDate.getTime())/ (1000 * 60 * 60 * 24));
        //ac 表示是否竞猜，默认是no
        String ac = "n";
        if(days >= 30){
             ac = "y";
        }

        AdReq[] adRqes =  ad.getAdReqs();

        Resp result = new Resp();
        result.setError(0);
        result.setMsg("success");
        BidResponse bidResponse = new BidResponse();
        bidResponse.setId(id);
        bidResponse.setBidId("5aaa961e80ac0eb6");
        bidResponse.setResType(0);
        Object[] adResps = new Object[adRqes.length];

        for(int i = 0; i < adRqes.length; i++){
            AdResp adResp = new AdResp();
            adResp.setToken(adRqes[i].getToken());
            adResp.setAc(ac);
            adResp.setProducts(new Long[]{1520031L, 1520072L});
            adResp.setBoostCoef(1.20F);
            adResp.setCacheTime(15);
            adResps[i] = adResp;
        }

        bidResponse.setAdResps(adResps);
        result.setBidResp(bidResponse);
        return result;
    }

}
