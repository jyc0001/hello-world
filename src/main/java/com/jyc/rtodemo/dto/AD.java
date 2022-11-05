package com.jyc.rtodemo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AD {

    String id;
    String v;
    String did;
    Integer didType;
    String imei2;
    Integer test;
    AdReq[] adReqs;

}
