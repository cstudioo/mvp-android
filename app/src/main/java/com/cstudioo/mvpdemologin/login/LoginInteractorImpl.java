package com.cstudioo.mvpdemologin.login;

import android.text.TextUtils;

import com.cstudioo.mvpdemologin.R;
import com.cstudioo.mvpdemologin.util.Utils;
import com.cstudioo.mvpdemologin.webservice.APIError;
import com.cstudioo.mvpdemologin.webservice.ErrorUtils;
import com.cstudioo.mvpdemologin.webservice.ServiceWrapper;
import com.cstudioo.mvpdemologin.webservice.model.request.Login;
import com.cstudioo.mvpdemologin.webservice.model.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cstudioo on 06/01/17.
 */

public class LoginInteractorImpl implements ILoginInteractor {

    private ServiceWrapper serviceInstance;

    @Override
    public void login(String userName, String passWord, IValidationErrorListener validationErrorListener, final IOnLoginFinishedListener loginFinishedListener) {
        if (isDataValid(userName, passWord, validationErrorListener)) {

            serviceInstance = new ServiceWrapper(null);

            Call<ResponseLogin> responseLoginCallback = serviceInstance.login(new Login(userName, passWord, ""));

            responseLoginCallback.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                    if (response.body() != null && response.isSuccess()) {
                        loginFinishedListener.getUserData(response.body());
                    } else {

                        if(response.errorBody() != null) {
                            APIError error = ErrorUtils.parseError(response);
                            loginFinishedListener.errorMsg(error.getMessage());
                        } else {
                            loginFinishedListener.errorMsg("Problem getting user !! Try again later.");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    loginFinishedListener.errorMsg("Problem getting user !! Try again later.");
                }
            });
        }
    }


    private boolean isDataValid(String userName, String password, IValidationErrorListener validationErrorListener) {

        if (TextUtils.isEmpty(userName)) {

            validationErrorListener.emailError(R.string.activity_login_enter_email);
            return false;

        } else if(!Utils.isValidEmail(userName)){

            validationErrorListener.emailError(R.string.activity_login_email_invalid);
            return false;

        } else if (TextUtils.isEmpty(password)) {

            validationErrorListener.passwordError(R.string.activity_login_enter_password);
            return false;

        } else if (password.length() < 6) {

            validationErrorListener.passwordError(R.string.activity_login_password_err);
            return false;

        } else {
            return true;
        }
    }
}
