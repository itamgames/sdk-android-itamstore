package com.itamgames.itamsdk.network;

import android.util.Log;

import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class RpcRequest extends EosApi {

//    public static final String BaseURL = "https://api-kylin.eosasia.one/";
    public static final String BaseURL = "https://api.eoslaomao.com/";

    static public void Accountinfo( String _acccountname, JSONObjectRequestListener listener ){
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("account_name", _acccountname);
            RequestPost( BaseURL + "v1/chain/get_account", jsonParam, listener );
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e( "famous TEST", "JSONException : " + e);
        }
    }

//    static public void Abijsontobin( String _code, String _action, String _from, String _to, String _quantity, String _memo, JSONObjectRequestListener listener ) {

    /* TODO transaction_type
    * transaction_type 0 : eos transfer
    * transaction_type 1 : cpu stake
    * transaction_type 2 : cpu unstake
    * transaction_type 3 : ram buy
    * transaction_type 4 : ram sell
    * */

}

//https://firebasestorage.googleapis.com/v0/b/itamtestapp.appspot.com/o/data%2Freviewinfo.json?alt=media&token=ed2997d2-7fe3-4828-a6ec-684389e2f887
