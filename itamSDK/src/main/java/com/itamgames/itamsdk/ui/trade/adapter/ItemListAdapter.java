package com.itamgames.itamsdk.ui.trade.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.data.ItemInfoStorage;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder> {

    final int ITEM_LIST_TAG = 2000;

    Context context;
    ArrayList<ItemInfoStorage> items;
    View.OnClickListener listener;

    public ItemListAdapter( Context _context, ArrayList<ItemInfoStorage> _items, View.OnClickListener _listener ){
        this.context = _context;
        this.items = _items;
        this.listener = _listener;
        Log.e( "famous TEST" , "items : " + this.items.size() );
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.e( "famous TEST" , "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trade_shop_item_row, parent, false );
        return new ItemListViewHolder( v );

    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder holder, int position) {
        Log.e( "famous TEST" , "onBindViewHolder : ");

        ItemInfoStorage item = items.get( position );

        holder.title.setText( item.title );
        holder.detail.setText( item.price );

        holder.cardview.setTag( position + ITEM_LIST_TAG );
        holder.cardview.setOnClickListener( this.listener );
    }

    @Override
    public int getItemCount() {
        Log.e( "famous TEST" , "getItemCount : " );
        return items.size();
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView detail;
        CardView cardview;

        public ItemListViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.TRADE_SHOP_ITEM_TITLE_TEXT);
            detail = (TextView) itemView.findViewById(R.id.TRADE_SHOP_ITAM_PRICE_TEXT);
            cardview = (CardView) itemView.findViewById(R.id.TRADE_SHOP_ITEM_ROW_LL);
//

        }
    }

}
