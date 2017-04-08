package com.cstudioo.mvpdemologin.login;

import com.cstudioo.mvpdemologin.util.ErrorCode;
import com.cstudioo.mvpdemologin.webservice.model.response.ResponseLogin;

/**
 * Created by cstudioo on 05/01/17.
 */

public interface ILoginInteractor {

    void login(String userName, String passWord, IValidationErrorListener validationErrorListener, IOnLoginFinishedListener loginFinishedListener);

    interface IOnLoginFinishedListener {

        void getUserData(ResponseLogin user);

        void errorMsg(String errorMsg);
    }

    interface IValidationErrorListener {

        void emailError(ErrorCode code);

        void passwordError(ErrorCode code);
    }
}
