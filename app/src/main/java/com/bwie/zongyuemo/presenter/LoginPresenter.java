package com.bwie.zongyuemo.presenter;

import com.bwie.zongyuemo.bean.LoginBean;
import com.bwie.zongyuemo.model.ILoginModel;
import com.bwie.zongyuemo.model.LoginModel;
import com.bwie.zongyuemo.net.OnNetListener;
import com.bwie.zongyuemo.view.ILoginView;



public class LoginPresenter {
    private ILoginModel iLoginModel;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModel();
    }
    public void setLogin(final String mobile, String pass){
        iLoginModel.getLogin(mobile, pass, new OnNetListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                String code = loginBean.getCode();
                String msg = loginBean.getMsg();
                iLoginView.showLogin(code,msg,loginBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    public void onDetachView(){
        if (iLoginView != null){
            iLoginView = null;
        }
    }
}
