package com.bwie.zongyuemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.zongyuemo.R;
import com.bwie.zongyuemo.bean.ProductsBean;
import com.bwie.zongyuemo.view.DetailActivity;
import com.bwie.zongyuemo.view.GoodsActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductsBean.DataBean> list;

    public RvAdapter(Context context, List<ProductsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rvitem, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        final ProductsBean.DataBean bean = list.get(position);
        String images = bean.getImages();
        String[] split = images.split("!");
        holder1.sdv.setImageURI(split[0]);
        holder1.name.setText(bean.getSubhead());
        holder1.price.setText("￥"+bean.getPrice());
        holder1.sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("pid",bean.getPid()+"");
                context.startActivity(intent);*/
                Intent intent = new Intent(context, GoodsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sdv;
        TextView name;
        TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.zi_sdv);
            name = itemView.findViewById(R.id.zi_tv1);
            price = itemView.findViewById(R.id.zi_price);
        }
    }
}
