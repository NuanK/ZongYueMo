package com.bwie.zongyuemo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.zongyuemo.R;
import com.bwie.zongyuemo.bean.RegistBean;
import com.bwie.zongyuemo.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
* 注册
* */
public class RegisterActivity extends AppCompatActivity implements IRegisterView {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass1)
    EditText pass1;
    @BindView(R.id.pass2)
    EditText pass2;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.register)
    Button register;
    private RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenter(this);
    }
    @OnClick({R.id.register})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.register:
                String mobil = name.getText().toString().trim();
                String pass = pass1.getText().toString().trim();
                registerPresenter.setRegist(mobil,pass);
                break;
        }
    }

    @Override
    public void showRegist(String code, String msg, RegistBean registBean) {
        if (code.equals("0")){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (code.equals("1")) {
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.onDetachView();
    }
}
