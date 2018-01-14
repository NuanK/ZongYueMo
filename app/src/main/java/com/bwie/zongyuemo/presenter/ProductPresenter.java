package com.bwie.zongyuemo.presenter;

import com.bwie.zongyuemo.bean.ProductsBean;
import com.bwie.zongyuemo.model.IProductModel;
import com.bwie.zongyuemo.model.ProductModel;
import com.bwie.zongyuemo.net.OnNetListener;
import com.bwie.zongyuemo.view.IProductView;



public class ProductPresenter {
    private IProductModel iProductModel;
    private IProductView iProductView;

    public ProductPresenter(IProductView iProductView) {
        this.iProductView = iProductView;
        iProductModel = new ProductModel();
    }

    public void setProduct(){
        iProductModel.getProduct(new OnNetListener<ProductsBean>() {
            @Override
            public void onSuccess(ProductsBean productsBean) {
                iProductView.showRegist(productsBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    public void onDetachView(){
        if (iProductView != null){
            iProductView = null;
        }
    }
}
