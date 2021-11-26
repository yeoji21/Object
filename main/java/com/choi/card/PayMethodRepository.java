package com.choi.card;

public interface PayMethodRepository {
    void save(PayMethod payMethod);

    PayMethod findByUserIdAndCardNum(String userId, String cardNo);

    PayMethod findById(Long id);
}
