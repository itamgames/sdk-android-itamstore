package com.itamgames.itamsdk.ui.buy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseView;

public class ItemInfoDlgView extends BaseView {

    Button close_btn = null;

    RelativeLayout buy_ll;
    Button buy_btn = null;

    RelativeLayout fin_ll;
    EditText fin_edit = null;
    Button accept_btn = null;

    RelativeLayout finger_ll;
    ImageView back_img = null;
    ImageView front_img = null;

    public ItemInfoDlgView(Context context) {

        super(context, R.layout.item_info_dialog_layout);

        close_btn = (Button)main_view.findViewById( R.id.TRADE_SHOP_ITEM_DLG_CLOSE_BTN );

        buy_ll = (RelativeLayout)main_view.findViewById( R.id.ITEM_BUY_LL );
        buy_btn = (Button)main_view.findViewById( R.id.TRADE_SHOP_ITEM_DLG_BUY_BTN );

        fin_ll = (RelativeLayout)main_view.findViewById( R.id.ITEM_BUY_FIN_LL );
        fin_edit = (EditText) main_view.findViewById( R.id.ITEM_BUY_FIN_EDIT );
        accept_btn = (Button)main_view.findViewById( R.id.ITEM_BUY_FIN_ACCEPT_BTN );

        finger_ll = (RelativeLayout)main_view.findViewById( R.id.ITEM_BUY_FINGER_LL );
        back_img = (ImageView) main_view.findViewById( R.id.ITEM_BUY_FINGER_BACK_IMG );
        front_img = (ImageView)main_view.findViewById( R.id.ITEM_BUY_FINGER_FRONT_IMG );

    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        super.setOnClickListener(l);

        close_btn.setOnClickListener( l );
        buy_btn.setOnClickListener( l );
        accept_btn.setOnClickListener( l );

    }

    public void HideBuyItemLayout(Animation _ani ){
        buy_ll.startAnimation( _ani );
        buy_ll.setVisibility( View.GONE );
    }

    public void ShowInputFinLayout( Animation _fani, Animation _eani ){

        buy_ll.startAnimation( _fani );
        buy_ll.setVisibility( View.GONE );

        fin_ll.startAnimation( _eani );
        fin_ll.setVisibility( View.VISIBLE );
    }

    public void ShowFingerLayout( Animation _fani, Animation _eani ){

        buy_ll.startAnimation( _fani );
        buy_ll.setVisibility( View.GONE );

        finger_ll.startAnimation( _eani );
        finger_ll.setVisibility( View.VISIBLE );
    }

    public void SuccessFingerImage( Animation _ani  ){
        front_img.startAnimation( _ani );
        front_img.setVisibility( View.VISIBLE );

    }


}
