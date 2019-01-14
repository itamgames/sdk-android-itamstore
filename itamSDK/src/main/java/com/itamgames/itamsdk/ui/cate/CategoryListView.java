package com.itamgames.itamsdk.ui.cate;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseView;

public class CategoryListView extends BaseView {

    LinearLayout filter_ll = null;
    public ListView category_list = null;
    public ListView filter_list = null;

    ImageView arrow_img = null;

    ImageButton filter_btn = null;

    TextView cate_title_text = null;

    /*TODO 세로 모드일때만*/
    TextView cpu_text, ram_text;

    int orientation;

    public CategoryListView(Context context, int orientation ) {

        super(context, R.layout.category_layout );

        this.orientation = orientation;

        if( this.orientation == Configuration.ORIENTATION_LANDSCAPE ){

            filter_ll = (LinearLayout)main_view.findViewById( R.id.CATE_FILTER_LL );

            filter_list = (ListView)main_view.findViewById( R.id.FITER_LIST );
            category_list = (ListView)main_view.findViewById( R.id.CATE_LIST );

            filter_btn = (ImageButton)main_view.findViewById( R.id.CATE_FILTER_BTN );

            arrow_img = (ImageView)main_view.findViewById( R.id.FILTER_ARROW_IMG );

            cate_title_text = (TextView)main_view.findViewById( R.id.CATE_LIST_TITLE_TEXT );

            filter_ll.setY( 700 );
        } else {
            cpu_text = (TextView)main_view.findViewById( R.id.CATE_CPU_TITLE_TEXT );
            ram_text = (TextView)main_view.findViewById( R.id.CATE_RAM_TITLE_TEXT );
        }

    }

    /*TODO 세로모드일때*/
    public void SetResourceText( boolean iscpu ){
        if( iscpu == true ){
            cpu_text.setTextColor( Color.parseColor("#230f3b"));
            ram_text.setTextColor( Color.parseColor("#cccccc"));
        } else {
            cpu_text.setTextColor( Color.parseColor("#cccccc"));
            ram_text.setTextColor( Color.parseColor("#230f3b"));
        }

    }
    /*TODO 세로모드일때*/
    public void ShowResource( boolean show ){

        if( show == true ){
            cpu_text.setVisibility( View.VISIBLE );
            ram_text.setVisibility( View.VISIBLE );
        } else {
            cpu_text.setVisibility( View.GONE );
            ram_text.setVisibility( View.GONE );
        }
    }


    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        super.setOnClickListener(l);

        if( this.orientation == Configuration.ORIENTATION_LANDSCAPE ){
            filter_btn.setOnClickListener( l );
        } else {
            cpu_text.setOnClickListener( l );
            ram_text.setOnClickListener( l );
        }


    }
    public void setOnItemClickListener( AdapterView.OnItemClickListener l) {

        if( this.orientation == Configuration.ORIENTATION_LANDSCAPE ){
            category_list.setOnItemClickListener( l );
            filter_list.setOnItemClickListener( l );
        }


    }


    public void FilterShowAnmation(final boolean flag ){

        float fy = 0.0f;
        float ty = 0.0f;

        float fd = 0.0f;
        float td = 0.0f;

        if( flag == true ){
            fy = 700.0f;
            ty = 0.0f;

            fd = 0.0f;
            td = 180.0f;

        } else {

            fy = 0.0f;
            ty = 700.0f;

            fd = 180.0f;
            td = 0.0f;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(fd, td, 10.0f, 10.0f );
        rotateAnimation.setDuration( 500 );
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setFillEnabled( true );
        arrow_img.startAnimation( rotateAnimation );
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, fy, ty);
        translateAnimation.setDuration(1000);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if( flag == true ){
                    filter_ll.setY( 10.0f );
                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if( flag == false ){
                    filter_ll.setY( 700.0f );
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        filter_ll.startAnimation(translateAnimation);

    }

    public void SetCateTitle( String _tmp ){
        cate_title_text.setText(_tmp);
    }

    public void SetCateGoryList( ListAdapter adapter  ){

        category_list.setAdapter( adapter );

    }

    public void ShowFilter(){
        filter_ll.setVisibility( View.VISIBLE );
    }
    public void HideFilter(){
        filter_ll.setVisibility( View.GONE  );
    }


}
