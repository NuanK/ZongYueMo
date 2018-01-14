package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.ShopCar;
import com.bwie.zongyuemo.net.RetrofitHelper;
import com.bwie.zongyuemo.presenter.CartPresenter;

import java.util.Map;

import io.reactivex.Flowable;



public class Model implements IGoodsModel{

    private CartPresenter presenter;

    public Model(CartPresenter presenter) {

        this.presenter = presenter;

    }

    @Override

    public void getData(Map<String, String> map) {

        Flowable<ShopCar> flowable = RetrofitHelper.getInstance().getApiService().getNews(map);

        presenter.get(flowable);

    }
}
