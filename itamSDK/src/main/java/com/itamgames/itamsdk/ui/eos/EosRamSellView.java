package com.itamgames.itamsdk.ui.eos;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseView;

public class EosRamSellView extends BaseView {

    EditText account_edit;
    EditText ramcount_edit;

    Button stake_btn;

    Button ramcount_reset_btn;
    Button ramcount_buy_1kb_btn;
    Button ramcount_buy_10kb_btn;
    Button ramcount_buy_100kb_btn;

    OnClickListener btnListener;

    public EosRamSellView(Context context ) {
        super(context, R.layout.eos_ram_sell_layout );

        ramcount_edit = (EditText)main_view.findViewById( R.id.EOS_RAM_SELL_INPUT_EDIT );

        stake_btn = (Button)main_view.findViewById( R.id.EOS_RAM_SELL_ACCEPT_BTN );

        ramcount_reset_btn = (Button)main_view.findViewById( R.id.EOS_RAM_SELL_RESET_BTN );
        ramcount_buy_1kb_btn = (Button)main_view.findViewById( R.id.EOS_RAM_SELL_1KB_BTN );
        ramcount_buy_10kb_btn = (Button)main_view.findViewById( R.id.EOS_RAM_SELL_10KB_BTN );
        ramcount_buy_100kb_btn = (Button)main_view.findViewById( R.id.EOS_RAM_SELL_100KB_BTN );

        ClickEvent();

        ramcount_reset_btn.setOnClickListener( btnListener );
        ramcount_buy_1kb_btn.setOnClickListener( btnListener );
        ramcount_buy_10kb_btn.setOnClickListener( btnListener );
        ramcount_buy_100kb_btn.setOnClickListener( btnListener );

    }
    void ClickEvent(){

        btnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                float tmp = 0.f;

                int i = v.getId();

                if (i == R.id.EOS_RAM_SELL_RESET_BTN) {
                    ramcount_edit.setText( "" );
                } else if (i == R.id.EOS_RAM_SELL_1KB_BTN) {
                    tmp = Float.parseFloat( GetRamBuyCount() ) + 1.0f;
                    ramcount_edit.setText( String.format( "%.4f", tmp ) );

                } else if (i == R.id.EOS_RAM_SELL_10KB_BTN) {

                    tmp = Float.parseFloat( GetRamBuyCount() ) + 10.0f;
                    ramcount_edit.setText( String.format( "%.4f", tmp ) );

                } else if (i == R.id.EOS_RAM_SELL_100KB_BTN) {

                    tmp = Float.parseFloat( GetRamBuyCount() ) + 100.0f;
                    ramcount_edit.setText( String.format( "%.4f", tmp ) );
                }
            }
        };
    }

    public String GetRamBuyCount( ){
        String tmp = ramcount_edit.getText().toString();
        if( tmp.length() <= 0 ){
            tmp = "0.000";
        }
        return tmp;
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        super.setOnClickListener(l);

        stake_btn.setOnClickListener( l );
    }

    public String GetRamCount(){

        return ramcount_edit.getText().toString();
    }



}
