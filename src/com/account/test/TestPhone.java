package com.account.test;

import com.account.utils.MobileMessageCheck;
import org.junit.Test;

import java.io.IOException;

public class TestPhone {
    @Test
    public void demo() throws IOException {
        //18725967698
        String phone = "18725967698";
        String code = MobileMessageCheck.checkMsg(phone);
        System.out.println(code);
    }
}
