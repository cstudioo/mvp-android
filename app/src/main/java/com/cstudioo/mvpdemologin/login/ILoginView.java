package com.cstudioo.mvpdemologin.login;

import com.cstudioo.mvpdemologin.util.ErrorCode;
import com.cstudioo.mvpdemologin.webservice.model.response.ResponseLogin;

/**
 * Created by cstudioo on 05/01/17.
 */

public interface ILoginView {

    void showLoading();
    void hideLoading();
    void setEmailError(ErrorCode code);
    void setPasswordError(ErrorCode code);
    void loginSuccess(ResponseLogin user);
    void loginFailure(ErrorCode code);
    void loginFailure(String errMsg);
}
