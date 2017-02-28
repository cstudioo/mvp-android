package com.cstudioo.mvpdemologin.login;

import com.cstudioo.mvpdemologin.webservice.model.response.ResponseLogin;

/**
 * Created by cstudioo on 05/01/17.
 */

public interface ILoginInteractor {

    void login(String userName, String passWord, IValidationErrorListener validationErrorListener, IOnLoginFinishedListener loginFinishedListener);

    interface IOnLoginFinishedListener {

        void getUserData(ResponseLogin user);

        void errorMsg(String msg);
    }

    interface IValidationErrorListener {

        void emailError(int msg);

        void passwordError(int msg);
    }
}
