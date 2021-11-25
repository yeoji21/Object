package com.choi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PayMethodRegisterTest {

    @Mock private PayMethodRegReqValidator mockValidator;
    private PayMethodRegister pr;
    @Mock private UserChecker mockUserChecker;
    @Mock private CardValidityChecker mockCardChecker;
    private PayMethodRepository payMethodRepository = new MemoryPayMethodRepository();


    @BeforeEach
    void setUp() {
        pr = new PayMethodRegister(mockValidator, mockUserChecker, mockCardChecker, payMethodRepository);
    }

    @Test
    public void invalidRegReq(){
        given(mockValidator.validate(any(PayMethodRegReq.class))).willReturn(Arrays.asList("some errors"));
        PayMethodRegReq regReq = new PayMethodRegReq(null, null, null);

        assertThrows(PayValidationException.class, () -> pr.register(regReq));
    }

    @Test
    public void noUser(){
        given(mockValidator.validate(any(PayMethodRegReq.class))).willReturn(Collections.emptyList());
        given(mockUserChecker.exists("111")).willReturn(false);

        PayMethodRegReq regReq = new PayMethodRegReq("111", null, null);
        assertThrows(NoUserException.class, () -> pr.register(regReq));
    }

    @Test
    public void invalidCardNum(){
        given(mockValidator.validate(any(PayMethodRegReq.class))).willReturn(Collections.emptyList());
        given(mockUserChecker.exists("111")).willReturn(true);
        given(mockCardChecker.checkValidity("1234")).willReturn(false);

        PayMethodRegReq regReq = new PayMethodRegReq("111", "1234", null);
        assertThrows(InvalidCardException.class, () -> pr.register(regReq));
    }

    @Test
    public void duplicatedCardNum(){
        given(mockValidator.validate(any(PayMethodRegReq.class))).willReturn(Collections.emptyList());
        given(mockUserChecker.exists("111")).willReturn(true);
        given(mockCardChecker.checkValidity("1234")).willReturn(true);
        payMethodRepository.save(new PayMethod(1L, "111", "1234", "me"));

        PayMethodRegReq regReq = new PayMethodRegReq("111", "1234", "me");
        assertThrows(DuplicatedCardNumException.class, () -> pr.register(regReq));
    }

    @Test
    public void registered(){
        given(mockValidator.validate(any(PayMethodRegReq.class))).willReturn(Collections.emptyList());
        given(mockUserChecker.exists("111")).willReturn(true);
        given(mockCardChecker.checkValidity("1234")).willReturn(true);
        PayMethodRegReq regReq = new PayMethodRegReq("111", "1234", "me");

        Long id = pr.register(regReq);
        assertThat(id).isNotNull();
        PayMethod payMethod = payMethodRepository.findById(1L);
        assertThat(payMethod.getId()).isEqualTo(1L);
        assertThat(payMethod.getOwner()).isEqualTo("me");
    }
}
