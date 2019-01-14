package com.itamgames.itamsdk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itamgames.itamsdk.ui.base.ItamInAppListener;

public class ItamloginButton extends RelativeLayout  {

    LinearLayout bg;
    ImageView symbol;
    TextView text;

    Context con;

    private ItamInAppListener mListener;

    public ItamloginButton(Context context ) {
        super(context);

        con = context;

        initView();
    }

    public ItamloginButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        con = context;

        initView();
        getAttrs( attrs );
    }

    public ItamloginButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initView();
        getAttrs(attrs, defStyleAttr );

    }

    private void initView() {

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.itamloginbutton, this, false);
        addView(v);

        bg = (LinearLayout) findViewById(R.id.bg);
        symbol = (ImageView) findViewById(R.id.symbol);

        text = (TextView) findViewById(R.id.text);

//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoAuthApp();
//            }
//        });

    }
    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginButton);

        setTypeArray(typedArray);
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginButton, defStyle, 0);
        setTypeArray(typedArray);

    }


    private void setTypeArray(TypedArray typedArray) {

//
//        int bg_resID = typedArray.getResourceId(R.styleable.LoginButton_bg, R.drawable.login_naver_bg);
//        bg.setBackgroundResource(bg_resID);
//
//        int symbol_resID = typedArray.getResourceId(R.styleable.LoginButton_symbol, R.drawable.login_naver_symbol);
//        symbol.setImageResource(symbol_resID);
//
//        int textColor = typedArray.getColor(R.styleable.LoginButton_textColor, 0);
//        text.setTextColor(textColor);
//
//        String text_string = typedArray.getString(R.styleable.LoginButton_text);
//        text.setText(text_string);


        typedArray.recycle();

    }

    public void gotoAuthApp(Activity act ){

        ApplicationInfo appInfo = null;
        try {
            appInfo = con.getPackageManager().getApplicationInfo(con.getPackageName(), PackageManager.GET_META_DATA );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Bundle aBundle = appInfo.metaData;
        String aValue = aBundle.getString("itamnetworkkey");

        ComponentName cn = new ComponentName("com.itamgames.itamapp","com.itamgames.itamapp.ItamAuthAct");

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.putExtra("key", aValue );
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        intent.setComponent(cn);

        act.startActivityForResult(intent, 1);

    }

}
