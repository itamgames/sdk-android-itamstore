package com.itamgames.itamsdk.recver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ItamInappReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        Log.e("famous TEST", "ItamInappReceiver action : " + action );

        if( action.equals( "com.itamgames.itamapp.signdata.response" ) == true ){
            String result = intent.getStringExtra( "result" );
            String msg = intent.getStringExtra( "data" );

            String dataString = String.format( "result : %s ",result ) + "\n" + String.format( "data : %s ",msg )  ;
            Toast.makeText( context, dataString, Toast.LENGTH_SHORT ).show();


        } else if( action.equals( "com.itamgames.itamapp.inapp.response" ) == true ) {

            String result = intent.getStringExtra( "result" );
            String msg = intent.getStringExtra( "data" );

            String dataString = String.format( "inapp result : %s ",result ) + "\n" + String.format( "data : %s ",msg )  ;

            Log.e( "famous TEST" , "ItamInappReceiver : " + dataString );
            Toast.makeText( context, dataString, Toast.LENGTH_SHORT ).show();
        }
    }

}
