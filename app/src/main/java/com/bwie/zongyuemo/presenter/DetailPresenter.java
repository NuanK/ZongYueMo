package com.bwie.zongyuemo.presenter;

import com.bwie.zongyuemo.bean.DetailBean;
import com.bwie.zongyuemo.model.DetailModel;
import com.bwie.zongyuemo.model.IDetailModel;
import com.bwie.zongyuemo.net.OnNetListener;
import com.bwie.zongyuemo.view.IDetailView;



public class DetailPresenter {
    private IDetailModel iDetailModel;
    private IDetailView iDetailView;

    public DetailPresenter(IDetailView iDetailView) {
        this.iDetailView = iDetailView;
        iDetailModel = new DetailModel();
    }
    public void setDetail(String pid){
        iDetailModel.getDetail(pid, new OnNetListener<DetailBean>() {
            @Override
            public void onSuccess(DetailBean detailBean) {
                iDetailView.show(detailBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    public void onDetachView(){
        if (iDetailView != null){
            iDetailView = null;
        }
    }
}
