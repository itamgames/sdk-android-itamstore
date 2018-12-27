package com.itamgames.gametestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ItamappReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if( action.equals( "com.itamgames.itamapp.signdata.response" ) ){

            String result = intent.getStringExtra( "result" );
            String msg = intent.getStringExtra( "data" );

            String dataString = String.format( "result : %s ",result ) + "\n" + String.format( "data : %s ",msg )  ;
            Toast.makeText( context, dataString, Toast.LENGTH_SHORT ).show();

        }
    }

}
