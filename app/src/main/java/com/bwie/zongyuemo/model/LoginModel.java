package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.LoginBean;
import com.bwie.zongyuemo.net.OnNetListener;
import com.bwie.zongyuemo.net.RetrofitHelper;
import com.bwie.zongyuemo.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class LoginModel implements ILoginModel {
    @Override
    public void getLogin(String mobile, String password, final OnNetListener<LoginBean> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.login(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        onNetListener.onSuccess(loginBean);
                    }
                });
    }
}
