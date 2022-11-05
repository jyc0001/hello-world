package com.jyc.rtodemo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Resp {

    Integer error;
    String msg;
    Object bidResp;
}
