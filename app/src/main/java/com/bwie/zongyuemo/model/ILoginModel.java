package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.LoginBean;
import com.bwie.zongyuemo.net.OnNetListener;



public interface ILoginModel {
    public void getLogin(String mobile, String password, OnNetListener<LoginBean> onNetListener);
}
