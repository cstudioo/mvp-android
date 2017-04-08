package com.cstudioo.mvpdemologin.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cstudioo.mvpdemologin.R;
import com.cstudioo.mvpdemologin.base.BaseActivity;
import com.cstudioo.mvpdemologin.home.HomeActivity;
import com.cstudioo.mvpdemologin.util.Constant;
import com.cstudioo.mvpdemologin.util.ErrorCode;
import com.cstudioo.mvpdemologin.util.Utils;
import com.cstudioo.mvpdemologin.webservice.model.response.ResponseLogin;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    private ProgressDialog mProgressDialog;
    private LoginPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {

        etEmail = (EditText) findViewById(R.id.activity_login_et_email);
        etPassword = (EditText) findViewById(R.id.activity_login_et_password);
        btnLogin = (Button) findViewById(R.id.activity_login_btn_login);

        mProgressDialog = new ProgressDialog(LoginActivity.this);

        btnLogin.setOnClickListener(this);

        mPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl());

    }

    @Override
    public void showLoading() {
        mProgressDialog.setTitle(null);
        mProgressDialog.setMessage(getResources().getString(R.string.activity_login_loading_msg));
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void setEmailError(ErrorCode code) {
        if (etEmail != null) {
            if(code.getId() == ErrorCode.ENTER_EMAIL.getId()) {
                etEmail.setError(getResources().getString(R.string.activity_login_enter_email));
            }else if (code.getId() == ErrorCode.EMAIL_INVALID.getId()){
                etEmail.setError(getResources().getString(R.string.activity_login_email_invalid));
            }
        }
    }

    @Override
    public void setPasswordError(ErrorCode code) {
        if (etPassword != null) {
            if(code.getId() == ErrorCode.ENTER_PASSWORD.getId()) {
                etPassword.setError(getResources().getString(R.string.activity_login_enter_password));
            }else if (code.getId() == ErrorCode.PASSWORD_INVALID.getId()){
                etPassword.setError(getResources().getString(R.string.activity_login_password_err));
            }
        }
    }

    @Override
    public void loginSuccess(ResponseLogin responseLogin) {
        Intent openHomeScreen = new Intent(LoginActivity.this, HomeActivity.class);
        openHomeScreen.putExtra(Constant.PASS_TO_HOME_MSG, "This is HOME");
        startActivity(openHomeScreen);
        finish();
    }

    @Override
    public void loginFailure(ErrorCode errorCode) {
        if(errorCode.getId() == 4) {
            Toast.makeText(this, getResources().getString(R.string.activity_login_fail_msg), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void loginFailure(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.activity_login_btn_login:

                if (etEmail != null && etPassword != null) {
                    if (Utils.isNetworkAvailable(LoginActivity.this)) {
                        mPresenter.callLogin(etEmail.getText().toString(), etPassword.getText().toString());
                    } else {
                        Utils.displayCommonAlertDialog(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.connection_issue_msg));
                    }
                }
                break;
        }
    }
}
