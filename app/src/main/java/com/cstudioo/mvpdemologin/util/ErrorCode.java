package com.cstudioo.mvpdemologin.util;

/**
 * Created by indianic on 08/04/17.
 */

public enum ErrorCode {
    ENTER_EMAIL(0),
    EMAIL_INVALID(1),
    ENTER_PASSWORD(2),
    PASSWORD_INVALID(3),
    LOGIN_FAILED(4);

    private final int id;

    ErrorCode(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
