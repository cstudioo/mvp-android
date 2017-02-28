package com.cstudioo.mvpdemologin.login;

import com.cstudioo.mvpdemologin.webservice.model.response.ResponseLogin;

/**
 * Created by cstudioo on 05/01/17.
 */

public interface ILoginView {

    void showLoading();
    void hideLoading();
    void setEmailError(int errRes);
    void setPasswordError(int errRes);
    void loginSuccess(ResponseLogin user);
    void loginFailure(int errMsg);
    void loginFailure(String errMsg);
}
