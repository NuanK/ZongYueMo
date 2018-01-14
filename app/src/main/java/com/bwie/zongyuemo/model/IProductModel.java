package com.bwie.zongyuemo.model;

import com.bwie.zongyuemo.bean.ProductsBean;
import com.bwie.zongyuemo.net.OnNetListener;



public interface IProductModel {
    public void getProduct(OnNetListener<ProductsBean> onNetListener);
}
