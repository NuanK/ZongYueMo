package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.LoginBean;
import com.bwie.zongyuemo.bean.RegistBean;
import com.bwie.zongyuemo.net.OnNetListener;



public interface IRegisterModel {
    public void getRegist(String mobile, String password, OnNetListener<RegistBean> onNetListener);
}
