package com.bwie.zongyuemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.zongyuemo.R;
import com.bwie.zongyuemo.adapter.MyShopAdapter;
import com.bwie.zongyuemo.bean.GoodBean;
import com.bwie.zongyuemo.bean.GroupBean;
import com.bwie.zongyuemo.bean.ShopCar;
import com.bwie.zongyuemo.presenter.CartPresenter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsActivity extends AppCompatActivity implements IView{

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_bianji)
    TextView mTvBianji;
    @BindView(R.id.exlist)
    ExpandableListView mExlist;
    @BindView(R.id.check_all)
    public CheckBox checkAll;
    @BindView(R.id.tv_zprice)
    TextView mTvZprice;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.btn_js)
    Button mBtnJs;

    ArrayList<GroupBean> groupBeen=new ArrayList<>();
    ArrayList<ArrayList<GoodBean>>goods=new ArrayList<>();
    private MyShopAdapter adapter;
    private boolean flagedit=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);

        CartPresenter presenter=new CartPresenter();
        presenter.attachView(this);
        Map<String, String> map = new HashMap<>();

        map.put("uid", "72");

        presenter.getData(map);

        adapter = new MyShopAdapter(this, groupBeen, goods, this);

        mExlist.setAdapter(adapter);

        mExlist.setGroupIndicator(null);

        for (int i = 0; i < adapter.getGroupCount(); i++) {

            mExlist.expandGroup(i);

        }

        adapter.notifyDataSetChanged();


    }

    @OnClick({R.id.iv_back, R.id.tv_bianji, R.id.check_all, R.id.btn_js})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_bianji:
                String trim = mTvBianji.getText().toString().trim();

                if (trim.equals("编辑")) {

                    mTvBianji.setText("完成");

                } else {

                    mTvBianji.setText("编辑");

                }

                for (List<GoodBean> i1 : goods) {

                    for (int r = 0; r < i1.size(); r++) {

                        i1.get(r).setBtn(flagedit);

                    }

                }

                flagedit = !flagedit;

                adapter.notifyDataSetChanged();
                break;
            case R.id.check_all:

                boolean checked = checkAll.isChecked();

                for (int i = 0; i < groupBeen.size(); i++) {

                    groupBeen.get(i).setGroupcheck(checked);

                }

                for (int q = 0; q < goods.size(); q++) {

                    ArrayList<GoodBean> goodsBeen = goods.get(q);

                    for (int j = 0; j < goodsBeen.size(); j++) {

                        goodsBeen.get(j).setGoodscheck(checked);

                    }

                }

                changesum(goods);

                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_js:
                int index = 0;

                for (int q = 0; q < goods.size(); q++) {

                    ArrayList<GoodBean> goodsBeen = goods.get(q);

                    for (int j = 0; j < goodsBeen.size(); j++) {

                        boolean goodscheck = goodsBeen.get(j).isGoodscheck();

                        if (goodscheck) {

                            index++;

                        }

                    }

                }

                if (index == 0) {

                    Toast.makeText(this, "请选择商品，谢谢", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this, mBtnJs.getText().toString(), Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    @Override
    public void OnSuccess(Object o) {
        if (o != null) {

            if (o instanceof List) {

                List<ShopCar.DataBean> data = (List<ShopCar.DataBean>) o;

                for (int i = 0; i < data.size(); i++) {

                    groupBeen.add(new GroupBean(false, data.get(i).getSellerName(), data.get(i).getSellerid()));

                    List<ShopCar.DataBean.ListBean> list = data.get(i).getList();

                    ArrayList<GoodBean> goodsBeen = new ArrayList<>();

                    for (int j = 0; j < list.size(); j++) {

                        goodsBeen.add(new GoodBean(false, list.get(j).getBargainPrice(),list.get(j).getPrice(), list.get(j).getImages(), list.get(j).getTitle(), list.get(j).getSubhead(), list.get(j).getNum(), list.get(j).getPid()));

                    }

                    goods.add(goodsBeen);

                }

                for (int i = 0; i < adapter.getGroupCount(); i++) {

                    mExlist.expandGroup(i);

                }

                adapter.notifyDataSetChanged();

            }

        }
    }

    @Override
    public void OnFailed(Exception e) {
        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
    }

    DecimalFormat df = new DecimalFormat("######0.00");



    public void changesum(ArrayList<ArrayList<GoodBean>> childBeen) {

        int count = 0;

        double sum = 0;

        for (List<GoodBean> i1 : childBeen) {

            for (int r = 0; r < i1.size(); r++) {

                boolean childCb1 = i1.get(r).isGoodscheck();

                if (childCb1) {

                    double price = i1.get(r).getBargainPrice();

                    int num = i1.get(r).getNum();

                    sum += price * num;

                    count+=num;

                }

            }

        }

        mTvZprice.setText("￥" + df.format(sum));

        mTvCount.setText(count + "");

    }



    public void deleteShop(int pid) {

        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();

    }


}
