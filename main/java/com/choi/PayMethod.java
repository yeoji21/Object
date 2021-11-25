package com.choi;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PayMethod {
    private Long id;
    private String userId;
    private String cardNum;
    private String owner;

    public void setId(long id) {
        this.id = id;
    }
}
