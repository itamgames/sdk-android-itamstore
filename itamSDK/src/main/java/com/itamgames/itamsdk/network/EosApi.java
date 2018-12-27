package com.itamgames.itamsdk.network;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class EosApi {

    static  public void RequestPost(String url , JSONObject json, JSONObjectRequestListener listener ){

        AndroidNetworking.post( url )
                .addJSONObjectBody(json)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(listener);
    }

    static  public void RequestGet(String url, JSONObjectRequestListener listener ){

        AndroidNetworking.get( url )
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(listener);
    }

}
