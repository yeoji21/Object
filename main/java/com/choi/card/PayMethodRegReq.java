package com.choi.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PayMethodRegReq {
    private String userId;
    private String cardNo;
    private String holderName;
}
