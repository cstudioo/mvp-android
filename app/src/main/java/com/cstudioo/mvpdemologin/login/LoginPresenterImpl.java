package com.cstudioo.mvpdemologin.login;

import com.cstudioo.mvpdemologin.util.ErrorCode;
import com.cstudioo.mvpdemologin.webservice.model.response.ResponseLogin;

/**
 * Created by cstudioo on 06/01/17.
 */

public class LoginPresenterImpl implements ILoginPresenter {


    ILoginView mILoginView;
    ILoginInteractor mILoginInteractor;

    public LoginPresenterImpl(ILoginView loginView, ILoginInteractor loginInteractor) {
        this.mILoginView = loginView;
        this.mILoginInteractor = loginInteractor;
    }


    @Override
    public void callLogin(String username, String password) {
        mILoginView.showLoading();
        mILoginInteractor.login(username, password, new ILoginInteractor.IValidationErrorListener() {
            @Override
            public void emailError(ErrorCode code) {
                mILoginView.hideLoading();
                mILoginView.setEmailError(code);
            }

            @Override
            public void passwordError(ErrorCode code) {
                mILoginView.hideLoading();
                mILoginView.setPasswordError(code);
            }

        }, new ILoginInteractor.IOnLoginFinishedListener() {
            @Override
            public void getUserData(ResponseLogin user) {
                mILoginView.hideLoading();
                if (user != null) {
                    mILoginView.loginSuccess(user);
                } else {
                    mILoginView.loginFailure(ErrorCode.LOGIN_FAILED);
                }
            }

            @Override
            public void errorMsg(String errorMsg) {
                mILoginView.hideLoading();
                mILoginView.loginFailure(errorMsg);
            }

        });
    }
}
