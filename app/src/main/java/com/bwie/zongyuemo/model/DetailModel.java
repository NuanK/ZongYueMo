package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.DetailBean;
import com.bwie.zongyuemo.net.OnNetListener;
import com.bwie.zongyuemo.net.RetrofitHelper;
import com.bwie.zongyuemo.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class DetailModel implements IDetailModel {
    @Override
    public void getDetail(String pid, final OnNetListener<DetailBean> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.detail(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(DetailBean detailBean) {
                        onNetListener.onSuccess(detailBean);
                    }
                });
    }
}
