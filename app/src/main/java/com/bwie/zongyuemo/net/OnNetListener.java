package com.bwie.zongyuemo.net;



public interface OnNetListener<T> {
    public void onSuccess(T t);
    public void onFailure(Exception e);
}
