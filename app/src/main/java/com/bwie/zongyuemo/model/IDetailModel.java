package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.DetailBean;
import com.bwie.zongyuemo.net.OnNetListener;



public interface IDetailModel {
    public void getDetail(String pid, OnNetListener<DetailBean> onNetListener);
}
