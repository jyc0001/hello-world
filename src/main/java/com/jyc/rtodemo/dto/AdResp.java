package com.jyc.rtodemo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdResp {
    String token;
    String ac;
    Long[] products;
    Float boostCoef;
    int cacheTime;

}
