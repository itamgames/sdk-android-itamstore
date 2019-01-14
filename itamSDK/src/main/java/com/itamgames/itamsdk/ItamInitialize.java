package com.itamgames.itamsdk;

import android.content.Context;
import android.content.IntentFilter;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.itamgames.itamsdk.recver.ItamInappReceiver;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class ItamInitialize {

    Context context;

    ItamInappReceiver receiver;

    public ItamInitialize( Context con ) {

        IntentFilter completeFilter = new IntentFilter();

        completeFilter.addAction("com.itamgames.itamapp.inapp.response");
        completeFilter.addAction("com.itamgames.itamapp.signdata.response");

        receiver = new ItamInappReceiver();
        con.registerReceiver( receiver , completeFilter );

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1000*60, TimeUnit.SECONDS)
                .readTimeout(1000*60, TimeUnit.SECONDS)
                . writeTimeout(1000*60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
        AndroidNetworking.initialize(con, okHttpClient);
    }

    public void UnItamSDK(){
        context.unregisterReceiver( receiver );
    }
}
