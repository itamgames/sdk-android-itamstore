package com.itamgames.itamsdk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.itamgames.itamsdk.R;

public class LoadingDlg extends Dialog {

    public LoadingDlg(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.loadingdialog);

        setCancelable( false );
    }
}


