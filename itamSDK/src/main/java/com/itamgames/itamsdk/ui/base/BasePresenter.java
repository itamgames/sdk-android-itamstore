package com.itamgames.itamsdk.ui.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.dialog.LoadingDlg;


public abstract class BasePresenter implements OnClickListener  {

    protected Context con;

    protected Handler messageHandler;
    protected long mLastClickTime;


    protected int STATUS_INDEX;
    protected LoadingDlg progressDialog;

    protected Animation slide_up = null;
    protected Animation slide_down = null;

    protected Animation left_slide_out = null;
    protected Animation right_slide_in = null;

    protected Animation left_slide_in = null;
    protected Animation right_slide_out = null;


    protected Animation fade_in = null;
    protected Animation fade_out = null;

    protected BasePresenter(Context c, Handler h ) {

        this.con = c;
        this.messageHandler = h;

        slide_up = AnimationUtils.loadAnimation( con, R.anim.anim_slide_up );
        slide_up.setInterpolator( AnimationUtils.loadInterpolator(con, android.R.anim.decelerate_interpolator) );

        slide_down = AnimationUtils.loadAnimation( con, R.anim.anim_slide_down );
        slide_down.setInterpolator( AnimationUtils.loadInterpolator(con, android.R.anim.accelerate_interpolator) );

        fade_in = AnimationUtils.loadAnimation( con, R.anim.fade_in );
        fade_out = AnimationUtils.loadAnimation( con, R.anim.fade_out );

        left_slide_out = AnimationUtils.loadAnimation( con, R.anim.anim_slide_out_left );
        right_slide_in = AnimationUtils.loadAnimation( con, R.anim.anim_slide_in_right );

        left_slide_in = AnimationUtils.loadAnimation( con, R.anim.anim_slide_in_left );
        right_slide_out = AnimationUtils.loadAnimation( con, R.anim.anim_slide_out_right );

    }

    protected Message SetMessage( int id, Object obj ){

        Message msg = new Message();
        msg.what = id;
        msg.obj = obj;
        return  msg;

    }

    @Override
    public void onClick(View v) {

    }

    protected void Init(){

    }


    public void showLoading() {

        this.progressDialog = new LoadingDlg(this.con);
        this.progressDialog.show(); // 보여주기

    }

    public void hideLoading() {

        if(this.progressDialog != null ){
            this.progressDialog.hide();
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
    }
}
