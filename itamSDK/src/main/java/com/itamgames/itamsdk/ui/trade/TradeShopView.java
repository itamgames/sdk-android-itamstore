package com.itamgames.itamsdk.ui.trade;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseView;
import com.itamgames.itamsdk.ui.trade.adapter.GameItemListAdapter;

public class TradeShopView extends BaseView {

//    RecyclerView itemlist = null;
    ListView itemlist = null;

    TextView itam_text = null;

    public TradeShopView(Context context) {

        super(context, R.layout.trade_shop_layout );

//        itemlist = (RecyclerView)main_view.findViewById( R.id.TRADE_SHOP_ITEM_LIST );
        itemlist = (ListView) main_view.findViewById( R.id.TRADE_SHOP_ITEM_LIST );
        itam_text = (TextView) main_view.findViewById( R.id.TRADE_SHOP_ITAM_COUNT_TEXT );


    }

    public void SetItamTokenCount( String _count ){
        itam_text.setText( _count );
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {

        super.setOnClickListener(l);

    }

//    public void SetItemList( ItemListAdapter adapter  ){
//
//        android.support.v7.widget.LinearLayoutManager layoutManager = new android.support.v7.widget.LinearLayoutManager(con);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        itemlist.setLayoutManager(layoutManager);
//        itemlist.setHasFixedSize(true);
//        itemlist.setAdapter( adapter );
//
//    }
    public void SetItemList( GameItemListAdapter adapter  ){

        itemlist.setAdapter( adapter );

    }

}
