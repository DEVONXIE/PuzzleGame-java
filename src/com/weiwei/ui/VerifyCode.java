package com.weiwei.ui;

import java.util.Random;

public class VerifyCode {
    public String getCode() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int tmp = r.nextInt(10);
            sb.append(tmp);
        }
        return sb.toString();
    }
}
