package com.bwie.zongyuemo.presenter;

import com.bwie.zongyuemo.bean.RegistBean;
import com.bwie.zongyuemo.model.IRegisterModel;
import com.bwie.zongyuemo.model.RegisterModel;
import com.bwie.zongyuemo.net.OnNetListener;
import com.bwie.zongyuemo.view.IRegisterView;


public class RegisterPresenter {
    private IRegisterModel iRegisterModel;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        iRegisterModel = new RegisterModel();
    }

    public void setRegist(final String mobile, String pass){
        iRegisterModel.getRegist(mobile, pass, new OnNetListener<RegistBean>() {
            @Override
            public void onSuccess(RegistBean registBean) {
                String code = registBean.getCode();
                String msg = registBean.getMsg();
                iRegisterView.showRegist(code,msg,registBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    public void onDetachView(){
        if (iRegisterView != null){
            iRegisterView = null;
        }
    }
}
