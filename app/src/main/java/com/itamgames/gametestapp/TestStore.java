package com.itamgames.gametestapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.itamgames.itamsdk.data.ItemInfoStorage;
import com.itamgames.itamsdk.ui.base.ItamInAppListener;
import com.itamgames.itamsdk.util.ItamInappHandler;

public class TestStore extends Activity implements View.OnClickListener, ItamInAppListener {


    Context con;

    ItamInappHandler itamInappHandler ;
    ItemInfoStorage info = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.teststore );

        con = this;

        findViewById( R.id.TEST_ITEM_01_BTN ).setOnClickListener( this );
        findViewById( R.id.TEST_ITEM_02_BTN ).setOnClickListener( this );

        info = new ItemInfoStorage();
        info.title = "나무 100개";
        info.price = "0.0001 EOS";
        info.memo = "나무를 100개 추가하였습니다. 부자네요.^^";

        itamInappHandler = new ItamInappHandler( con,info,this);

    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            case R.id.TEST_ITEM_01_BTN : {


                itamInappHandler.StartTransationSerivce();
            }
            break;
            case R.id.TEST_ITEM_02_BTN : {

            }
            break;
        }
    }

    @Override
    public void onAuthApplicataion(String authResult) {

    }

    @Override
    public void onResponseProduct(String product) {

        Toast.makeText(con, "onResponseProduct " + product, Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void onResponsePayment(String result) {

    }

}
