package com.choi;

import java.util.List;

public class PayMethodRegister {
    private PayMethodRegReqValidator validator;
    private UserChecker userChecker;
    private CardValidityChecker cardChecker;
    private PayMethodRepository payMethodRepository;

    public PayMethodRegister(PayMethodRegReqValidator validator, UserChecker userChecker, CardValidityChecker cardChecker, PayMethodRepository payMethodRepository) {
        this.validator = validator;
        this.userChecker = userChecker;
        this.cardChecker = cardChecker;
        this.payMethodRepository = payMethodRepository;
    }


    public Long register(PayMethodRegReq regReq) {
        List<String> errors = validator.validate(regReq);
        if (!errors.isEmpty())
            throw new PayValidationException();
        if (!userChecker.exists(regReq.getUserId()))
            throw new NoUserException();
        if(!cardChecker.checkValidity(regReq.getCardNo()))
            throw new InvalidCardException();
        PayMethod payMethod = payMethodRepository.findByUserIdAndCardNum(regReq.getUserId(), regReq.getCardNo());
        if (payMethod != null)
            throw new DuplicatedCardNumException();
        PayMethod newPayMethod = new PayMethod(null, regReq.getUserId(), regReq.getCardNo(), regReq.getHolderName());
        payMethodRepository.save(newPayMethod);
        return newPayMethod.getId();
    }
}
