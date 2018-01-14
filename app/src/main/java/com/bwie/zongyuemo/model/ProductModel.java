package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.ProductsBean;
import com.bwie.zongyuemo.net.OnNetListener;
import com.bwie.zongyuemo.net.RetrofitHelper;
import com.bwie.zongyuemo.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class ProductModel implements IProductModel {
    @Override
    public void getProduct(final OnNetListener<ProductsBean> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.product()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(ProductsBean productsBean) {
                        onNetListener.onSuccess(productsBean);
                    }
                });
    }
}
