package com.cstudioo.mvpdemologin.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cstudioo.mvpdemologin.BuildConfig;
import com.cstudioo.mvpdemologin.util.Constant;
import com.cstudioo.mvpdemologin.webservice.model.request.Login;
import com.cstudioo.mvpdemologin.webservice.model.response.ResponseLogin;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p/>
 * This Wrapper class will be mediator of service interface and calling class.
 * Whatever the features of retrofit and okhttp api will implement, need to be put into this class.
 * We can put common webservice security features to this class so that we can increase the reusability of code.
 */
public class ServiceWrapper {

    private ServiceInterface service;
    private Retrofit retrofit;
    private Interceptor mHeaderInterceptor;

    public ServiceWrapper(Interceptor headerInterceptor) {
        mHeaderInterceptor = headerInterceptor;
        retrofit = getRetrofit();
        service = retrofit.create(ServiceInterface.class);
    }

    public Retrofit getRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client;

        if (mHeaderInterceptor != null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(mHeaderInterceptor)
//                    .cookieJar(new JavaNetCookieJar(cookieManager))
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(90, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG)
                builder.addInterceptor(interceptor);
            client = builder.build();
        } else {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                    .cookieJar(new JavaNetCookieJar(cookieManager))
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(90, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG)
                builder.addInterceptor(interceptor);
            client = builder.build();
        }

        Gson gson = new GsonBuilder()
//                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.WS_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }

    public Call<ResponseLogin> login(Login callingLogin) {
        return service.login(callingLogin);
    }


}
