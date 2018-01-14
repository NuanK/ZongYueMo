package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.LoginBean;
import com.bwie.zongyuemo.bean.RegistBean;
import com.bwie.zongyuemo.net.OnNetListener;
import com.bwie.zongyuemo.net.RetrofitHelper;
import com.bwie.zongyuemo.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class RegisterModel implements IRegisterModel {
    @Override
    public void getRegist(String mobile, String password, final OnNetListener<RegistBean> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.regist(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegistBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(RegistBean registBean) {
                        onNetListener.onSuccess(registBean);
                    }
                });
    }
}
