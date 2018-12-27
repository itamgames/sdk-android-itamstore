package com.itamgames.itamsdk.ui.trade.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.data.ItemInfoStorage;

import java.util.ArrayList;

public class GameItemListAdapter extends ArrayAdapter<String> {


    final int ITEM_LIST_TAG = 2000;

    Context context;
    ArrayList<ItemInfoStorage> items;
    View.OnClickListener listener;

    public GameItemListAdapter(@NonNull Context context, ArrayList<ItemInfoStorage> objects, View.OnClickListener l ) {
        super(context, R.layout.trade_shop_item_row);

        this.context = context;
        this.items = objects;
        this.listener = l;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.trade_shop_item_row, null);

            convertView.setTag( position + ITEM_LIST_TAG );
            convertView.setOnClickListener( this.listener );

        } else {

        }
        return convertView;
    }
}