package com.bwie.zongyuemo.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bwie.zongyuemo.view.GoodsActivity;
import com.bwie.zongyuemo.R;
import com.bwie.zongyuemo.bean.GoodBean;
import com.bwie.zongyuemo.bean.GroupBean;
import com.bwie.zongyuemo.view.AddDeleteView;

import java.util.ArrayList;
import java.util.List;



public class MyShopAdapter extends BaseExpandableListAdapter{

    Context context;

    ArrayList<GroupBean> groupBeen;

    ArrayList<ArrayList<GoodBean>> goods;

    GoodsActivity goodsActivity;



    public MyShopAdapter(Context context, ArrayList<GroupBean> groupBeen, ArrayList<ArrayList<GoodBean>> goods, GoodsActivity goodsActivity) {

        this.context = context;

        this.groupBeen = groupBeen;

        this.goods = goods;

        this.goodsActivity = goodsActivity;

    }



    @Override

    public int getGroupCount() {

        if (groupBeen != null) {

            return groupBeen.size();

        }

        return 0;

    }



    @Override

    public int getChildrenCount(int groupPosition) {

        if (goods.get(groupPosition) != null) {

            return goods.get(groupPosition).size();

        }

        return 0;

    }



    @Override

    public Object getGroup(int groupPosition) {

        return groupBeen.get(groupPosition);

    }



    @Override

    public Object getChild(int groupPosition, int childPosition) {

        return goods.get(groupPosition).get(childPosition);

    }



    @Override

    public long getGroupId(int groupPosition) {

        return groupPosition;

    }



    @Override

    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;

    }



    @Override

    public boolean hasStableIds() {

        return false;

    }


    @Override

    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.gwc_group_item, null);

        final CheckBox check_group = (CheckBox) view.findViewById(R.id.check_gwc_group);

        TextView group = (TextView) view.findViewById(R.id.tv_gwc_group);

        check_group.setChecked(groupBeen.get(groupPosition).isGroupcheck());

        group.setText(groupBeen.get(groupPosition).getSellerName());

        check_group.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                GroupBean group = groupBeen.get(groupPosition);

                group.setGroupcheck(check_group.isChecked());

                for (int j = 0; j < groupBeen.size(); j++) {

                    boolean groupCheck = groupBeen.get(j).isGroupcheck();

                    if (!groupCheck) {

                        goodsActivity.checkAll.setChecked(false);

                        break;

                    } else {

                        goodsActivity.checkAll.setChecked(true);

                    }

                }

                ArrayList<GoodBean> goodsBeen = goods.get(groupPosition);

                for (int i = 0; i < goodsBeen.size(); i++) {

                    goodsBeen.get(i).setGoodscheck(check_group.isChecked());

                }

                //计算价格

                goodsActivity.changesum(goods);

                notifyDataSetChanged();

            }

        });

        return view;

    }



    @Override

    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.gwc_goods_item, null);

        TextView tv_goods = (TextView) view.findViewById(R.id.tv_gwc_goods);

        TextView price = (TextView) view.findViewById(R.id.tv_gwc_price);

        TextView yprice = (TextView) view.findViewById(R.id.tv_gwc_yprice);

        final CheckBox check_goods = (CheckBox) view.findViewById(R.id.check_gwc_goods);

        ImageView img_goods = (ImageView) view.findViewById(R.id.img_gwc_goods);

        Button btn_delete = (Button) view.findViewById(R.id.btn_gwc_delete);

        final AddDeleteView adv = (AddDeleteView) view.findViewById(R.id.add_delete);

        tv_goods.setText(goods.get(groupPosition).get(childPosition).getTitle());

        price.setText(goods.get(groupPosition).get(childPosition).getPrice()+"");

        yprice.setText(goods.get(groupPosition).get(childPosition).getBargainPrice() + "");

        yprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);

        check_goods.setChecked(goods.get(groupPosition).get(childPosition).isGoodscheck());

        String images = goods.get(groupPosition).get(childPosition).getImages();

        String[] split = images.split("\\|");

//        String s=split[0];

//        Uri uri=Uri.parse(s);

        Glide.with(img_goods.getContext()).load(split[0]).into(img_goods);

        adv.setNumber(goods.get(groupPosition).get(childPosition).getNum());

        if (goods.get(groupPosition).get(childPosition).isBtn()) {

            btn_delete.setVisibility(View.VISIBLE);

        } else {

            btn_delete.setVisibility(View.INVISIBLE);

        }

        adv.setOnAddDelClickListener(new AddDeleteView.OnAddDelClickListener() {

            @Override

            public void onAddClick(View v) {

                int number = adv.getNumber();

                number++;

                adv.setNumber(number);

                goods.get(groupPosition).get(childPosition).setNum(number);

                goodsActivity.changesum(goods);

            }



            @Override

            public void onDelClick(View v) {

                int number = adv.getNumber();

                if (number > 1) {

                    number--;

                }

                if (number<=1){

                    Toast.makeText(context,"数量最小也得为1！",Toast.LENGTH_SHORT).show();

                }

                adv.setNumber(number);

                goods.get(groupPosition).get(childPosition).setNum(number);

                goodsActivity.changesum(goods);

            }

        });

        btn_delete.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                goodsActivity.deleteShop(goods.get(groupPosition).get(childPosition).getPid());

                int size = goods.get(groupPosition).size();

                if (goods.get(groupPosition).get(childPosition).isGoodscheck()) {

                    if (size == 1) {

                        goods.remove(groupPosition);

                        groupBeen.remove(groupPosition);

                    } else {

                        goods.get(groupPosition).remove(childPosition);

                    }

                    goodsActivity.changesum(goods);

                    notifyDataSetChanged();

                } else {

                    Toast.makeText(context, "请选择商品。。。", Toast.LENGTH_SHORT).show();

                }

            }

        });

        check_goods.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //定义一个默认flag

                boolean flag = false;

                //获得当前二级列表复选框的状态

                boolean cchecked = check_goods.isChecked();

                //把当前状态存储到二级列表List集合里面

                goods.get(groupPosition).get(childPosition).setGoodscheck(cchecked);



                //循环遍历二级列表List集合

                for (List<GoodBean> i1 : goods) {

                    // 按照所有二级列表的item的个数进行循环遍历

                    for (int r = 0; r < i1.size(); r++) {

                        //获取当前二级列表的状态

                        boolean childCb1 = i1.get(r).isGoodscheck();

                        //取反设置 如果为true改为false 如果为false改为true

                        if (!childCb1) {

                            //如果当前二级列表中有一条复选框是false  全选为false

                            goodsActivity.checkAll.setChecked(false);

                            //如果当前二级列表中有一条复选框是false  一级列表为false

                            groupBeen.get(groupPosition).setGroupcheck(false);

                            //flag为true跳出循环

                            flag = true;

                            break;

                        } else {

                            //如果所有的二级列表都为true  全选为true

                            goodsActivity.checkAll.setChecked(true);

                            //如果所有的二级列表都为true  一级列表为true

                            groupBeen.get(groupPosition).setGroupcheck(true);

                        }

                    }

                    //falg为true时跳出循环

                    if (flag) {

                        break;

                    }

                }

                //当前二级列表的总长度

                int size = goods.get(groupPosition).size();

                //按照当前二级列表的总长度循环

                for (int x = 0; x < size; x++) {

                    //获得当前二级列表中每一个item的选中状态

                    boolean childCb1 = goods.get(groupPosition).get(x).isGoodscheck();

                    //判断

                    if (!childCb1) {

                        //有一个flase 一级列表就设置false  跳出循环

                        groupBeen.get(groupPosition).setGroupcheck(false);

                        break;

                    } else {

                        groupBeen.get(groupPosition).setGroupcheck(true);

                    }

                }

                //计算价格

                goodsActivity.changesum(goods);

                //刷新适配器

                notifyDataSetChanged();

            }

        });

        return view;

    }



    @Override

    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return false;

    }



}
