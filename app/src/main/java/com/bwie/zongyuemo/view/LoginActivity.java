package com.bwie.zongyuemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.zongyuemo.R;
import com.bwie.zongyuemo.bean.LoginBean;
import com.bwie.zongyuemo.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
* 登录
* */
public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.regist)
    TextView regist;
    @BindView(R.id.login)
    Button login;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDetachView();
    }

    @OnClick({R.id.regist, R.id.login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.regist:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                String mobil = mobile.getText().toString().trim();
                String pass = password.getText().toString().trim();
                loginPresenter.setLogin(mobil,pass);
                break;
        }
    }

    @Override
    public void showLogin(String code, String msg, LoginBean loginBean) {
        if (code.equals("0")){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(LoginActivity.this, ProductActivity.class);
            startActivity(intent2);
        } else if (code.equals("1")) {
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
