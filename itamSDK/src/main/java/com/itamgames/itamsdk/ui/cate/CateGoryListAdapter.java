package com.itamgames.itamsdk.ui.cate;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.itamgames.itamsdk.R;

import java.util.ArrayList;

public class CateGoryListAdapter extends ArrayAdapter<String> {

    Context con;

    ArrayList<String> stringarray;

    View.OnClickListener listener;

    ViewHolder viewHolder;

    private int mSelectedPosition = 0;

    int index = 0;

    public int GetSelectPosition() {
        return mSelectedPosition;
    }

    public int GetIndex() {
        return index;
    }

    public void SetSelectPosition( int num ){
        mSelectedPosition = num;
    }

    public void SetIndex( int num ){
        index = num;
    }

    public CateGoryListAdapter(@NonNull Context context, ArrayList<String> objects, View.OnClickListener l ) {
        super(context, R.layout.category_row_layout);

        this.stringarray = new ArrayList<>();

        this.con = context;
        this.stringarray = objects;
        this.listener = l;

    }

    public void SetListdata( ArrayList<String> obj ){

        mSelectedPosition = 0;
        index = 0;

        ArrayList<String>tmplist = new ArrayList<>(obj);

        this.stringarray.clear();
        this.stringarray = null;
        this.stringarray = new ArrayList<String>(tmplist);

        for( int i = 0; i < stringarray.size(); i++ ){
            Log.e( "famous TEST" , "stringarray : " + stringarray.get( i ) );
        }
        Log.e( "famous TEST" , "obj : " + obj.size() );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            addAll( stringarray );
        }else{
            for (String item : stringarray )
                add(item);
        }

        notifyDataSetChanged();



    }
    @Override
    public int getCount() {
        return stringarray.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(con).inflate(R.layout.category_row_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.title.setTag( position + 3000 );
        if( position == mSelectedPosition) {
            viewHolder.title.setTextColor(Color.parseColor( "#230f3b") );
        } else {
            viewHolder.title.setTextColor(Color.parseColor( "#cccccc") );
        }

        viewHolder.title.setText(this.stringarray.get(position) );

        viewHolder.title.setOnClickListener( listener );

        return convertView;
    }

    private class ViewHolder {

        TextView title;

        public ViewHolder( View view) {
            title = (TextView)view.findViewById(R.id.CATE_ROW_TITLE_TEXT);
        }
    }
}