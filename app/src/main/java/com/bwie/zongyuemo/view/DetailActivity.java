package com.bwie.zongyuemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.zongyuemo.R;
import com.bwie.zongyuemo.bean.DetailBean;
import com.bwie.zongyuemo.presenter.DetailPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements IDetailView {
    @BindView(R.id.de_sdv)
    SimpleDraweeView sdv;
    @BindView(R.id.de_tv1)
    TextView tv1;
    /**
     * ￥
     */
    @BindView(R.id.de_price)
    TextView price;
    /**
     * 评价
     */
    @BindView(R.id.de_pj)
    TextView pj;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.buy)
    ImageView buy;
    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        detailPresenter = new DetailPresenter(this);
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        detailPresenter.setDetail(pid);
    }

    @Override
    public void show(DetailBean detailBean) {
        DetailBean.DataBean data = detailBean.getData();
        String[] img = data.getImages().split("//|");
        sdv.setImageURI(img[0]);
        tv1.setText(data.getSubhead());
        price.setText(data.getPrice()+"");
    }
    @OnClick({R.id.buy})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.buy:
                Intent intent = new Intent(DetailActivity.this, GoodsActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.onDetachView();
    }
}
