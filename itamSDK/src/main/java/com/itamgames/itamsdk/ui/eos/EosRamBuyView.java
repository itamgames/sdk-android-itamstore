package com.itamgames.itamsdk.ui.eos;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseView;

public class EosRamBuyView extends BaseView {

    EditText account_edit;
    EditText ramcount_edit;

    Button stake_btn;

    Button ramcount_reset_btn;
    Button ramcount_buy_1kb_btn;
    Button ramcount_buy_10kb_btn;
    Button ramcount_buy_100kb_btn;

    ImageView label_img = null;

    OnClickListener btnListener;

    RadioGroup change_rg = null;

    boolean iseos = true;

    public EosRamBuyView(Context context ) {
        super(context, R.layout.eos_ram_buy_layout );

        account_edit = (EditText)main_view.findViewById( R.id.EOS_RAM_BUY_RECV_ACCOUNT_EDIT );
        ramcount_edit = (EditText)main_view.findViewById( R.id.EOS_RAM_BUY_EDIT );

        stake_btn = (Button)main_view.findViewById( R.id.EOS_RAM_BUY_ACCEPT_BTN );

        ramcount_reset_btn = (Button)main_view.findViewById( R.id.EOS_RAM_BUY_RESET_BTN );
        ramcount_buy_1kb_btn = (Button)main_view.findViewById( R.id.EOS_RAM_BUY_1KB_BTN );
        ramcount_buy_10kb_btn = (Button)main_view.findViewById( R.id.EOS_RAM_BUY_10KB_BTN );
        ramcount_buy_100kb_btn = (Button)main_view.findViewById( R.id.EOS_RAM_BUY_100KB_BTN );

        change_rg = (RadioGroup)main_view.findViewById( R.id.EOS_RAM_BUY_RADIO_GROUP );

        label_img = (ImageView)main_view.findViewById( R.id.EOS_RAM_LABEL_IMG );

        ClickEvent();

        stake_btn.setOnClickListener( btnListener );
        ramcount_reset_btn.setOnClickListener( btnListener );
        ramcount_buy_1kb_btn.setOnClickListener( btnListener );
        ramcount_buy_10kb_btn.setOnClickListener( btnListener );
        ramcount_buy_100kb_btn.setOnClickListener( btnListener );

    }

    void ClickEvent(){

        change_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.RAM_BUY_EOS_UNI_BTN) {
                    label_img.setBackgroundResource(R.drawable.eos_label);
                    ramcount_edit.setText("");
                    ChangeEOSUni();
                } else if (checkedId == R.id.RAM_BUY_KB_UNI_BTN) {
                    label_img.setBackgroundResource(R.drawable.kb_label);
                    ramcount_edit.setText("");
                    ChangeKBUni();
                }
            }
        });

        btnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                float tmp = 0.f;

                int i = v.getId();

                if (i == R.id.EOS_RAM_BUY_RESET_BTN) {
                    ramcount_edit.setText( "" );
                } else if (i == R.id.EOS_RAM_BUY_1KB_BTN) {
                    tmp = Float.parseFloat( GetRamBuyCount() ) + 1.0f;
                    ramcount_edit.setText( String.format( "%.4f", tmp ) );

                } else if (i == R.id.EOS_RAM_BUY_10KB_BTN) {

                    tmp = Float.parseFloat( GetRamBuyCount() ) + 10.0f;
                    ramcount_edit.setText( String.format( "%.4f", tmp ) );

                } else if (i == R.id.EOS_RAM_BUY_100KB_BTN) {

                    tmp = Float.parseFloat( GetRamBuyCount() ) + 100.0f;
                    ramcount_edit.setText( String.format( "%.4f", tmp ) );
                }
            }
        };
    }

    public boolean GetRamType(){
        return iseos;
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

    public String GetRamBuyAccount(){

        return account_edit.getText().toString();
    }

    public String GetRamCount(){

        return ramcount_edit.getText().toString();
    }

    public void SetAccountEdit( String _tmp ){
        account_edit.setText( _tmp );
    }

    public void ChangeKBUni(){
        ramcount_buy_1kb_btn.setText( "+ 1KB" );
        ramcount_buy_10kb_btn.setText( "+ 10KB" );
        ramcount_buy_100kb_btn.setText( "+ 100KB" );
        iseos = false;
    }

    public void ChangeEOSUni(){
        ramcount_buy_1kb_btn.setText( "+ 1EOS" );
        ramcount_buy_10kb_btn.setText( "+ 10EOS" );
        ramcount_buy_100kb_btn.setText( "+ 100EOS" );
        iseos = true;
    }

}
