package com.itamgames.gametestapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itamgames.itamsdk.ItamInitialize;
import com.itamgames.itamsdk.ui.ItamSdkMainact;

public class main extends Activity {

    Button login_btn = null;
    Button download_btn = null;

    Button opensdk = null;
    Button shop_btn = null;

    private long downloadingStartTimeMillis;

    private boolean isCompleteAnimationPending;
    ImageView downloadingView;
    private boolean isDownloading;

    Context con = null;

    private String selectedImagePath;

    String accountname  = "";
    String publickey = "";
    String itamtoken = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.main );

        con = this;

        login_btn = (Button)findViewById( R.id.TESTGAME_LOGIN_BTN );
        shop_btn = (Button)findViewById( R.id.TEST_STORE_BTN );

        login_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAuthApp();
            }
        });

        shop_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( con, TestStore.class );
                startActivity( i );
            }
        });

        String version = "";
        try {
            PackageInfo i = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = i.versionName;
        } catch(PackageManager.NameNotFoundException e) { }

        TextView tex = (TextView) findViewById( R.id.MAIN_TEXT );
        tex.setText( "Version : " + version );

        Button recvbtn = (Button)findViewById(R.id.TEST_REQUEST_BTN );
        recvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent( con, TestActivity.class );
                startActivity(  i );
            }
        });

        downloadingView = (ImageView)findViewById( R.id.LOADING_IMG );

        download_btn = (Button)findViewById( R.id.TEST_DOWNLOAD_BTN );

        download_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCompleteAnimationPending) {
                    return;
                }
                if (isDownloading) {
                    final long delayMillis = 2666 - ((System.currentTimeMillis() - downloadingStartTimeMillis) % 2666);
                    downloadingView.postDelayed(() -> {
                        swapAnimation(R.drawable.avd_downloading_finish);
                        isCompleteAnimationPending = false;
                    }, delayMillis);
                    isCompleteAnimationPending = true;
                } else {
                    swapAnimation(R.drawable.avd_downloading_begin);
                    downloadingStartTimeMillis = System.currentTimeMillis();
                }
                isDownloading = !isDownloading;
            }
        });

        opensdk = (Button)findViewById( R.id.TEST_OPENSDK_BTN );
        opensdk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                GotoTradeShop();
            }
        });

        ItamInitialize itam = new ItamInitialize( con );

    }

    void GotoTradeShop(){
        Intent i = new Intent( this, ItamSdkMainact.class );
        i.putExtra( "accountname" , accountname );
        i.putExtra( "publickey" , publickey );
        i.putExtra( "itamtoken" , itamtoken );

        startActivity(  i );
//                Intent i = new Intent( this, TestActivity.class );
//        startActivity(  i );
    }

//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
//        Log.e( "famous TEST" , "startActivityForResult" );
//
//        String accountname  = intent.getStringExtra( "itamaccount" );
//        String publickey = intent.getStringExtra( "publickey" );
//
//        Log.e( "famous TEST" , "accountname : "  + accountname);
//        Log.e( "famous TEST" , "publickey : "  + publickey);
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e( "famous TEST" ,"onActivityResult" );
        accountname  = data.getStringExtra( "itamaccount" );
        publickey = data.getStringExtra( "publickey" );
        itamtoken = data.getStringExtra( "itamtoken" );

        Log.e( "famous TEST" , "accountname : "  + accountname);
        Log.e( "famous TEST" , "publickey : "  + publickey);


    }

    void gotoAuthApp(){

        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Bundle aBundle = appInfo.metaData;
        String aValue = aBundle.getString("itamnetworkkey");

//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("itamapp://auth?itamnetworkkey=" + aValue ) );
//        startActivity(intent);

//
        ComponentName cn = new ComponentName("com.itamgames.itamapp","com.itamgames.itamapp.ItamAuthAct");

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.putExtra("key", aValue );
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        intent.setComponent(cn);

        startActivityForResult(intent, 1);


    }

    private void swapAnimation(@DrawableRes int drawableResId) {
        final Drawable avd = AnimatedVectorDrawableCompat.create(this, drawableResId);
        downloadingView.setImageDrawable(avd);
        ((Animatable) avd).start();
    }


}
