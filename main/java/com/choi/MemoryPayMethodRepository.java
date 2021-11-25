package com.choi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryPayMethodRepository implements PayMethodRepository {

    private Map<Long, PayMethod> map = new HashMap<>();
    private AtomicLong payMethodId = new AtomicLong(1);

    @Override
    public void save(PayMethod payMethod) {
        payMethod.setId(payMethodId.getAndIncrement());
        map.put(payMethod.getId(), payMethod);
    }

    @Override
    public PayMethod findByUserIdAndCardNum(String userId, String cardNo) {
        return map.values().stream().filter(c -> c.getCardNum().equals(cardNo) && c.getUserId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public PayMethod findById(Long id) {
        return map.values().stream().filter(c -> c.getId()==id).findFirst().orElse(null);
    }
}
