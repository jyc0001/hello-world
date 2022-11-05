package com.jyc.rtodemo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BidResponse {
    String id;
    String bidId;
    Integer resType;
    Object[] adResps;

}
