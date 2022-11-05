package com.jyc.rtodemo.dto2;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AD {

    String requestId;
    String imeiMd5;
    String oaid;
    Integer app_id;
    Integer sex;
    Integer age;
    String city;
    String clientIp;
    String ownerId;
    String requestTime;
    String channelName;
    List<Integer> adTypeList;
    String sign;

}
