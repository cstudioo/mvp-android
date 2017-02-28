package com.cstudioo.mvpdemologin.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cstudioo.mvpdemologin.R;
import com.cstudioo.mvpdemologin.base.BaseActivity;
import com.cstudioo.mvpdemologin.home.HomeActivity;
import com.cstudioo.mvpdemologin.util.Constant;
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
    public void setEmailError(int errRes) {
        if (etEmail != null) {
            etEmail.setError(getResources().getString(errRes));
        }
    }

    @Override
    public void setPasswordError(int errRes) {
        if (etPassword != null) {
            etPassword.setError(getResources().getString(errRes));
        }
    }

    @Override
    public void loginSuccess(ResponseLogin responseLogin) {
        Log.e("loginSuccess", "Email : " + responseLogin.getEmail());
        Log.e("loginSuccess", "User name : " + responseLogin.getNickname());
        Log.e("loginSuccess", "User ID : " + responseLogin.getId());

        Intent openHomeScreen = new Intent(LoginActivity.this, HomeActivity.class);
        openHomeScreen.putExtra(Constant.PASS_TO_HOME_MSG, "This is HOME");
        startActivity(openHomeScreen);
        finish();
    }

    @Override
    public void loginFailure(int errMsg) {
        Toast.makeText(this, getResources().getString(errMsg), Toast.LENGTH_LONG).show();
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
                        mPresenter.login(etEmail.getText().toString(), etPassword.getText().toString());
                    } else {
                        Utils.displayCommonAlertDialog(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.connection_issue_msg));
                    }
                }
                break;
        }
    }
}
