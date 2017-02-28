package com.cstudioo.mvpdemologin.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.cstudioo.mvpdemologin.R;
import com.cstudioo.mvpdemologin.base.BaseActivity;
import com.cstudioo.mvpdemologin.util.Constant;

/**
 * Created by cstudioo on 11/01/17.
 */

public class HomeActivity extends BaseActivity {

    private TextView tvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvMsg.setText(getIntent().getStringExtra(Constant.PASS_TO_HOME_MSG));

    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void init() {

        tvMsg = (TextView) findViewById(R.id.activity_home_tv_msg);
    }
}
