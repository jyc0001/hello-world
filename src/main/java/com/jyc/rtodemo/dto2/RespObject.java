package com.jyc.rtodemo.dto2;

import com.alibaba.fastjson.JSONObject;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RespObject {

    int ret;
    String msg;
    JSONObject data;


}
