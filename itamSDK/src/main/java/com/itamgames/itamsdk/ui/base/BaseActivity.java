package com.itamgames.itamsdk.ui.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.itamgames.itamsdk.ui.dialog.LoadingDlg;

public class BaseActivity extends Activity implements OnClickListener {

    protected Context con = null;

    protected Handler messageHandler = null;

    protected LoadingDlg progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        con = this;

        requestWindowFeature(Window.FEATURE_NO_TITLE );
        setContentView(getResources().getIdentifier(this.getClass().getSimpleName().toLowerCase(), "layout",
                getPackageName()));


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onClick(View v) {

    }

    public void showLoading() {

        progressDialog = new LoadingDlg(this.con);
        progressDialog.show(); // 보여주기

    }

    public void hideLoading() {

        if(progressDialog != null ){
            progressDialog.hide();
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
