package com.bwie.zongyuemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.zongyuemo.R;
import com.bwie.zongyuemo.adapter.RvAdapter;
import com.bwie.zongyuemo.bean.ProductsBean;
import com.bwie.zongyuemo.presenter.ProductPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
* 商品列表
* */
public class ProductActivity extends AppCompatActivity implements IProductView {
    @BindView(R.id.rv)
    RecyclerView rv;
    private ProductPresenter productPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        productPresenter = new ProductPresenter(this);
        productPresenter.setProduct();
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productPresenter.onDetachView();
    }

    @Override
    public void showRegist(ProductsBean productsBean) {
        List<ProductsBean.DataBean> data = productsBean.getData();
        RvAdapter adapter = new RvAdapter(this, data);
        rv.setAdapter(adapter);
    }
}
