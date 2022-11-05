package com.jyc.rtodemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jyc.rtodemo.dto2.AD;
import com.jyc.rtodemo.dto2.RespObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestDemo2 {


    @RequestMapping(value = "test2", method = RequestMethod.POST)
    @ResponseBody
    public RespObject test(AD ad){

        System.out.println(ad);

        RespObject result = new RespObject();
        result.setRet(0);
        result.setMsg("");
        JSONObject data = new JSONObject();
        List<Integer> agreeTypeList = new ArrayList<>();
        agreeTypeList.add(1);
        agreeTypeList.add(3);
        List<Long> productList = new ArrayList<>();
        productList.add(1001L);
        productList.add(1002L);

        data.put("id", "4be50d1519eccf1c5e14bbdfaa2eb853");
        data.put("agree_type_list", agreeTypeList);
        data.put("product_list", productList);
        result.setData(data);
        return result;
    }
}
