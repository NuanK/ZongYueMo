package com.bwie.zongyuemo.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {
    private static OkHttpClient client;
    private static ServerApi serverApi;
    private static Retrofit retrofit;
    private static volatile RetrofitHelper instance;
    static {
        getClient();
    }

    private RetrofitHelper(){
        retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://120.27.23.105/")
                .build();
    }

    //底层封装OkHttp
    public static OkHttpClient getClient() {
        if (client == null){
            synchronized (RetrofitHelper.class){
                if (client == null){
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }

    public static RetrofitHelper getInstance(){
        if (instance==null){
            synchronized (RetrofitHelper.class){
                if (instance==null){
                    instance=new RetrofitHelper();
                }
            }
        }
        return instance;
    }

    public ServerApi getApiService(){
        ServerApi apiService=retrofit.create(ServerApi.class);
        return apiService;
    }



    public static ServerApi getServerApi() {
        if (serverApi == null){
            synchronized (ServerApi.class){
                if (serverApi == null){
                    serverApi = onCreate(ServerApi.class,Api.HOST);
                }
            }
        }
        return serverApi;
    }

    public static <T> T onCreate(Class<T> tClass, String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(tClass);
    }
}
