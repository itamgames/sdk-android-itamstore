package com.itamgames.itamsdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.itamgames.itamsdk.data.ItemInfoStorage;
import com.itamgames.itamsdk.ui.base.ItamInAppListener;
import com.itamgames.itamsdk.util.ItamInappHandler;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;


public class ConnectUnityAct extends UnityPlayerActivity {


    static ItemInfoStorage info;

    static ItamInappHandler itamInappHandler ;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
//        Intent intent = new Intent( this, com.itamgames.itamsdk.ui.ItamSdkMainact.class );
//        startActivity( intent );
//        finish();
        Log.e( "famous TREST" ,"ConnectUnityAct onCreate");
    }

    public static void CallItamActivity( String name, Context c ){

//        Intent enableBtIntent = new Intent( com.itamgames.itamsdk.ui.ItamSdkMainact.class );
//        UnityPlayer.currentActivity.startActivity(enableBtIntent);
        Log.e( "famous TREST" ,"name : " + name);
        Log.e( "famous TREST" ,"Context : " + c);
        Intent intent = new Intent(c,com.itamgames.itamsdk.ui.ItamSdkMainact.class);
//        intent.putExtra("name",name);
        c.startActivity(intent);


//        Intent intent = new Intent( con, com.itamgames.itamsdk.ui.ItamSdkMainact.class );
//        con.startActivity( intent );
//        Intent mapIntent = new Intent("com.itamgames.itamsdk.ui.ItamSdkMainact");
//        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        con.startActivity(mapIntent);
//        Log.e( "famous TREST" ,"CallItamActivity");
////        ctx.startActivityForResult(mapIntent, 0);
//        //In order to call Unity function
//        UnityPlayer.UnitySendMessage("MapLogic", "called", "yes");

    }

    public static void CallInAppActivity(final Context c, String name, String detail, final float price, String productid ){

//        Intent enableBtIntent = new Intent( com.itamgames.itamsdk.ui.ItamSdkMainact.class );
//        UnityPlayer.currentActivity.startActivity(enableBtIntent);
        Log.e( "famous TREST" ,"name : " + name);
        Log.e( "famous TREST" ,"detail : " + detail);
        Log.e( "famous TREST" ,"price : " + price);
        Log.e( "famous TREST" ,"productid : " + productid);

        info = new ItemInfoStorage();
        info.title = name;
        info.price =  String.format( "%.4f EOS", price );
        info.memo = detail;
        info.productid = productid;

        itamInappHandler = new ItamInappHandler(c, info, new ItamInAppListener() {
            @Override
            public void onAuthApplicataion(String authResult) {

            }

            @Override
            public void onResponseProduct(String product) {
                // 오마르 0
                // 찰스 1
                // 60센트 2
//                Toast.makeText(c, "onResponseProduct " + product, Toast.LENGTH_SHORT ).show();
                if( product.matches( "success" )){
                    UnityPlayer.UnitySendMessage("UICharacterShop(Clone)","onResponseProduct", info.productid  );
                } else {
//                    Toast.makeText(c, "결제 실패" + product, Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onResponsePayment(String result) {

            }
        });

        itamInappHandler.StartTransationSerivce();

    }

}
