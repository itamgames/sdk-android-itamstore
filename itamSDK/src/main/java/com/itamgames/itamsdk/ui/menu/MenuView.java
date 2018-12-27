package com.itamgames.itamsdk.ui.menu;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseView;

public class MenuView extends BaseView {

    Button trade_btn = null;
    Button myinven_btn = null;
    Button eos_resource_btn = null;

    ImageView trade_img = null;
    ImageView myinven_img = null;
    ImageView eosresource_img = null;

    TextView trade_text = null;
    TextView myinven_text = null;
    TextView eos_resource_text = null;

    public MenuView(Context context ) {
        super(context, R.layout.menu_layout);

        trade_btn = (Button)main_view.findViewById( R.id.TRADE_SHOP_BTN );
        myinven_btn = (Button)main_view.findViewById( R.id.MY_INVEN_BTN );
        eos_resource_btn = (Button)main_view.findViewById( R.id.EOS_RESOURCE_BTN );

        trade_img = (ImageView) main_view.findViewById( R.id.TRADE_SHOP_IMG );
        myinven_img = (ImageView) main_view.findViewById( R.id.MY_INVEN_IMG );
        eosresource_img = (ImageView) main_view.findViewById( R.id.EOS_RESOURCE_IMG );

        trade_text = (TextView) main_view.findViewById( R.id.TRADE_SHOP_TEXT );
        myinven_text = (TextView) main_view.findViewById( R.id.MY_INVEN_TEXT );
        eos_resource_text = (TextView) main_view.findViewById( R.id.EOS_RESOURCE_TEXT );

    }

    @Override
    public void setOnClickListener( OnClickListener l) {
        trade_btn.setOnClickListener( l );
        myinven_btn.setOnClickListener( l );
        eos_resource_btn.setOnClickListener( l );
    }

    public void EnableTradeShop(){
        trade_img.setBackgroundResource( R.drawable.trade_shop_enable_img );
        myinven_img.setBackgroundResource( R.drawable.item_inven_disable_img );
        eosresource_img.setBackgroundResource( R.drawable.eos_resource_disable_img );

        trade_text.setTextColor( Color.parseColor("#471b6b") );
        myinven_text.setTextColor( Color.parseColor("#cccccc") );
        eos_resource_text.setTextColor( Color.parseColor("#cccccc") );
    }

    public void EnableInventory(){
        trade_img.setBackgroundResource( R.drawable.trade_shop_disable_img );
        myinven_img.setBackgroundResource( R.drawable.item_inven_enable_img );
        eosresource_img.setBackgroundResource( R.drawable.eos_resource_disable_img );

        trade_text.setTextColor( Color.parseColor("#cccccc") );
        myinven_text.setTextColor( Color.parseColor("#471b6b") );
        eos_resource_text.setTextColor( Color.parseColor("#cccccc") );
    }

    public void EnableEosResource(){
        trade_img.setBackgroundResource( R.drawable.trade_shop_disable_img );
        myinven_img.setBackgroundResource( R.drawable.item_inven_disable_img );
        eosresource_img.setBackgroundResource( R.drawable.eos_resource_enable_img );

        trade_text.setTextColor( Color.parseColor("#cccccc") );
        myinven_text.setTextColor( Color.parseColor("#cccccc") );
        eos_resource_text.setTextColor( Color.parseColor("#471b6b") );
    }
}
