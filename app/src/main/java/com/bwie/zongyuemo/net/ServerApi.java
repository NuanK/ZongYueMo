package com.bwie.zongyuemo.net;

import com.bwie.zongyuemo.bean.DetailBean;
import com.bwie.zongyuemo.bean.LoginBean;
import com.bwie.zongyuemo.bean.ProductsBean;
import com.bwie.zongyuemo.bean.RegistBean;
import com.bwie.zongyuemo.bean.ShopCar;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;


public interface ServerApi {
    @GET(Api.LOGIN)
    Observable<LoginBean> login(@Query("mobile") String mobile, @Query("password") String password);
    @GET(Api.REGIST)
    Observable<RegistBean> regist(@Query("mobile") String mobile, @Query("password") String password);
    @GET(Api.PRODUCTS)
    Observable<ProductsBean> product();
    @GET(Api.DETAIL)
    Observable<DetailBean> detail(@Query("pid") String pid);
    //http://120.27.23.105/product/getCarts
    @GET("product/getCarts")
    Flowable<ShopCar> getNews(@QueryMap Map<String, String> map);
}
