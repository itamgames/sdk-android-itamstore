package com.itamgames.itamsdk.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class BaseView extends View implements LayoutView {

    protected View main_view = null;

    protected Context con = null;


    public BaseView(Context context, int layoutid ) {

        super(context  );

        con = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        main_view =  inflater.inflate( layoutid, null );

    }

//    protected View GetMainView() {
//        return main_view;
//    }

    @Override
    public View GetView() {
        return main_view;
    }

    @Override
    public void ShowView() {

        setVisibility( View.VISIBLE );

    }

    @Override
    public void HideView() {

        setVisibility( View.GONE );
    }
}
