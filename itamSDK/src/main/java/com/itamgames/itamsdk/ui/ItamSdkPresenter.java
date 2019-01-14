package com.itamgames.itamsdk.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.data.ItemInfoStorage;
import com.itamgames.itamsdk.data.UserInfoStorage;
import com.itamgames.itamsdk.ui.base.BasePresenter;
import com.itamgames.itamsdk.ui.cate.CateGoryListAdapter;
import com.itamgames.itamsdk.ui.cate.CategoryListView;
import com.itamgames.itamsdk.ui.eos.EosCpuStakeView;
import com.itamgames.itamsdk.ui.eos.EosCpuUnStakeView;
import com.itamgames.itamsdk.ui.eos.EosRamBuyView;
import com.itamgames.itamsdk.ui.eos.EosRamSellView;
import com.itamgames.itamsdk.ui.eos.EosResourceView;
import com.itamgames.itamsdk.ui.menu.MenuView;
import com.itamgames.itamsdk.ui.trade.TradeShopView;
import com.itamgames.itamsdk.ui.trade.adapter.GameItemListAdapter;
import com.itamgames.itamsdk.util.ProgressBarAnimation;

import java.util.ArrayList;

public class ItamSdkPresenter extends BasePresenter  {

    MenuView menuView = null;
    CategoryListView catelistview = null;
    TradeShopView tradeview = null;

    EosCpuStakeView eosCpuStakeView = null;
    EosCpuUnStakeView eosCpuUnStakeView = null;

    EosRamBuyView eosRamBuyView = null;
    EosRamSellView eosRamSellView = null;

    EosResourceView resourceView = null;
    ArrayList<ItemInfoStorage> itemList = null;
    ArrayList<String> cateliststring = null;
    String tmpcatelist [] = { "전체" , "무기", "방어구", "악세사리", "마법 아이템", "퀘스트 아이템", "일반 아이템", "레어 아이템", "전설 아이템" };
    String resourcecatelist [] = { "Stake" , "RAM" };

    final int CATEGORY_TAG = 3000;
    final int ITEM_LIST_TAG = 2000;

    boolean filter_enable = true;

    CateGoryListAdapter cateAdapter;

    int VIEW_STATE = 0; // 0 : 거래소 1 : 내 장비 2 : 자원


    public UserInfoStorage userInfoStorage;

    ProgressBarAnimation CpuProgressAni = null;
    ProgressBarAnimation BandProgressAni = null;
    ProgressBarAnimation RamProgressAni = null;

    int orientation;

    ItamSdkPresenter(Context c, Handler h, int orientation) {
        super(c, h);

        this.orientation = orientation;

        userInfoStorage = new UserInfoStorage();

        menuView = new MenuView( con );
        tradeview = new TradeShopView( con );
        resourceView = new EosResourceView( con, this.orientation  );

        eosCpuStakeView = new EosCpuStakeView( con );
        eosCpuUnStakeView = new EosCpuUnStakeView( con );
        eosRamBuyView = new EosRamBuyView( con );
        eosRamSellView = new EosRamSellView( con );

        itemList = new ArrayList<>();

        catelistview = new CategoryListView( con, orientation );

        if( orientation == Configuration.ORIENTATION_LANDSCAPE ){

            cateliststring = new ArrayList<>();

            for( int i = 0; i < tmpcatelist.length; i++ ){

                cateliststring.add( tmpcatelist[i]  );
            }
            cateAdapter = new CateGoryListAdapter( con, cateliststring, this );
            catelistview.SetCateGoryList( cateAdapter );
        }

        catelistview.setOnClickListener( this );
        menuView.setOnClickListener( this );

        resourceView.setOnClickListener( this );

        eosCpuStakeView.setOnClickListener( this );
        eosCpuUnStakeView.setOnClickListener( this );
        eosRamBuyView.setOnClickListener( this );
        eosRamSellView.setOnClickListener( this );

        /*TODO 최초 EOS자원으로 설정*/

        VIEW_STATE = 2;
        menuView.EnableEosResource();
//            catelistview.HideFilter();
//            catelistview.SetCateTitle("Resource");
        SetResourceCategory();

//        catelistview.setOnItemClickListener( this );
//        catelistview.category_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e( "famous TEST" , "VIEW_STATE : " + VIEW_STATE );
//                Log.e( "famous TEST" , "onItemClick : " + position );
//                if(VIEW_STATE == 0 ){
//
//                } else if(VIEW_STATE == 1 ){
//
//                } else if(VIEW_STATE == 2 ){
//
//                    if( position == 0 ){
//
//                        resourceView.ShowCpuLayout();
//                    } else {
//                        resourceView.ShowRamLayout();
//                    }
//                }
//            }
//        });

    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.TRADE_SHOP_BTN) {
            VIEW_STATE = 0;
            menuView.EnableTradeShop();

            if( orientation == Configuration.ORIENTATION_LANDSCAPE ){
                catelistview.ShowFilter();
                catelistview.SetCateTitle("Category");
                SetItemCategory();
            }else {
                catelistview.ShowResource(false);
            }
            messageHandler.sendEmptyMessage( 1 );

        } else if( i == R.id.MY_INVEN_BTN ){
            VIEW_STATE = 1;
            menuView.EnableInventory();
            if( orientation == Configuration.ORIENTATION_LANDSCAPE ){
                catelistview.ShowFilter();
                catelistview.SetCateTitle("Category");
                SetItemCategory();
            }else {
                catelistview.ShowResource(false);
            }
            messageHandler.sendEmptyMessage( 1 );
        } else if( i == R.id.EOS_RESOURCE_BTN ){
            VIEW_STATE = 2;
            menuView.EnableEosResource();
            if( orientation == Configuration.ORIENTATION_LANDSCAPE ){
                catelistview.HideFilter();
                catelistview.SetCateTitle("Resource");
                SetResourceCategory();
            }else {
                catelistview.ShowResource(true);
            }
            messageHandler.sendEmptyMessage( 0 );
        } else if( i == R.id.CATE_FILTER_BTN ){
            if(filter_enable == true  ){
                filter_enable = false;
                catelistview.FilterShowAnmation( true );
            } else {
                filter_enable = true;
                catelistview.FilterShowAnmation( false );
            }

        } else  if( i == R.id.EOS_CPU_STAKE_BTN ){

            Message msg = new Message();
            msg.what = 6;
            msg.arg1 = 0;
            messageHandler.sendMessage( msg );

        } else if( i == R.id.EOS_CPU_UNSTAKE_BTN ) {

            Message msg = new Message();
            msg.what = 6;
            msg.arg1 = 1;
            messageHandler.sendMessage( msg );

        } else if( i == R.id.EOS_RAM_BUY_BTN ) {

            Message msg = new Message();
            msg.what = 6;
            msg.arg1 = 2;
            messageHandler.sendMessage( msg );

        } else if( i == R.id.EOS_RAM_SELL_BTN ) {

            Message msg = new Message();
            msg.what = 6;
            msg.arg1 = 3;
            messageHandler.sendMessage( msg );

        } else if( i == R.id.EOS_STAKE_OPT_ACCEPT_BTN ) {

            Message msg = new Message();
            msg.what = 8;
            msg.arg1 = 1;
            messageHandler.sendMessage( msg );

        } else if( i == R.id.EOS_UNSTAKE_OPT_ACCEPT_BTN ) {

            Message msg = new Message();
            msg.what = 8;
            msg.arg1 = 2;
            messageHandler.sendMessage( msg );

        } else if( i == R.id.EOS_RAM_BUY_ACCEPT_BTN ){

            Message msg = new Message();
            msg.what = 8;
            msg.arg1 = 3;
            messageHandler.sendMessage( msg );

        } else if( i == R.id.EOS_RAM_SELL_ACCEPT_BTN ){
            Message msg = new Message();
            msg.what = 8;
            msg.arg1 = 4;
            messageHandler.sendMessage( msg );
        } else if( i == R.id.CATE_CPU_TITLE_TEXT ){
            catelistview.SetResourceText(true);
            Message msg = new Message();
            msg.what = 7;
            msg.arg1 = 0;
            messageHandler.sendMessage( msg );
        } else if( i == R.id.CATE_RAM_TITLE_TEXT ){
            catelistview.SetResourceText(false);
            Message msg = new Message();
            msg.what = 7;
            msg.arg1 = 1;
            messageHandler.sendMessage( msg );
        }
        else {

            ItemListClicked( (int)v.getTag() );
            CateListClicked( (int)v.getTag() );
        }
    }

    void SetItemCategory(){

        cateliststring.clear();
        cateAdapter.clear();
        for( int i = 0; i < tmpcatelist.length; i++ ){

            cateliststring.add( tmpcatelist[i]  );
        }
        cateAdapter.SetListdata( cateliststring );
        catelistview.SetCateGoryList( cateAdapter );

    }

    void SetResourceCategory(){
//
//        cateliststring.clear();
//        cateAdapter.clear();
//
//        for( int i = 0; i < resourcecatelist.length; i++ ){
//
//            cateliststring.add( resourcecatelist[i]  );
//        }
//        cateAdapter.SetListdata( cateliststring );
//        catelistview.SetCateGoryList( cateAdapter );
    }

    void SetTradeShop(){
        for( int i = 0; i < 10; i++ ){

            ItemInfoStorage buf = new ItemInfoStorage();

            buf.title ="아이템1";
            buf.price = "1.000";

            itemList.add( buf );
        }

        GameItemListAdapter itemListAdapter = new GameItemListAdapter( con, itemList, this );
        tradeview.SetItemList( itemListAdapter );
    }


    public void ItemListClicked( int tagid ){

        int idx = 0;

        if( tagid < ITEM_LIST_TAG + 100 && tagid >= ITEM_LIST_TAG ){
            idx =  tagid - ITEM_LIST_TAG;
            messageHandler.sendEmptyMessage( 2 );

        }

    }

    public void CateListClicked( int tagid ){

        int idx = 0;

        if( tagid < CATEGORY_TAG + 100 && tagid >= CATEGORY_TAG ){
            idx =  tagid - CATEGORY_TAG;

            cateAdapter.SetSelectPosition( idx ); //mSelectedPosition = position;
            cateAdapter.SetIndex(  idx ); //index = position;
            cateAdapter.notifyDataSetChanged();

            if(VIEW_STATE == 0 ){

            } else if(VIEW_STATE == 1 ){

            } else if(VIEW_STATE == 2 ){
                Log.e( "famous TEST" , "CateListClicked : " + tagid );

                Message msg = new Message();
                msg.what = 7;
                msg.arg1 = idx;
                messageHandler.sendMessage( msg );

            }
        }
    }

    public void SetItamToken( String _count ) {

        eosCpuUnStakeView.SetAccountEdit( userInfoStorage.userAccount );
        eosCpuStakeView.SetAccountEdit( userInfoStorage.userAccount );
        eosRamBuyView.SetAccountEdit( userInfoStorage.userAccount );
//        eosRamSellView.SetAccountEdit( userInfoStorage.userAccount );

        tradeview.SetItamTokenCount( _count );
    }

    public void SetEosInfo(){

        if( this.orientation == Configuration.ORIENTATION_LANDSCAPE ){
            resourceView.SetAccountName( userInfoStorage.userAccount );
            resourceView.SetEosCountText( userInfoStorage.eostoken );

            resourceView.SetEosStake( userInfoStorage.eosstake );
            resourceView.SetEosUnStake( userInfoStorage.eosunstake );
            resourceView.SetEosRefund(userInfoStorage.refund );
        }

    }

    void SetRamResource(){

        if (RamProgressAni == null) {

            RamProgressAni = new ProgressBarAnimation(resourceView.getRamUsebar(), 1000);

            float avernum = (this.userInfoStorage.useram / this.userInfoStorage.totalram) * 100.0f;
            String average = String.format( "%d", (int)avernum );

            resourceView.SetEosRamPersent( "(" + average +"%) " + "사용중" );
            resourceView.SetEosCpuMs( String.format( "%.4f KB", userInfoStorage.useram )  );
            resourceView.SetEosCpuSec( String.format( "%.3f KB", userInfoStorage.totalram )  );

            resourceView.SetRamProgress( (int)userInfoStorage.totalram );
            RamProgressAni.setProgress( (int)userInfoStorage.useram );

        } else {

            RamProgressAni.ResetProgress();
            RamProgressAni.reset();
            RamProgressAni.setProgress( (int)userInfoStorage.useram );
            RamProgressAni.start();

        }

    }
    void SetCpuResource(){

        if( CpuProgressAni == null || BandProgressAni == null ){

            CpuProgressAni = new ProgressBarAnimation(resourceView.getCpuUsebar(), 1000);
            BandProgressAni = new ProgressBarAnimation(resourceView.getBandbar(), 1000);

            float cpuavernum = (this.userInfoStorage.usecpu / this.userInfoStorage.totalcpu) * 100.0f;
            String cpuaverage = String.format( "%d", (int)cpuavernum );

            resourceView.SetEosCpuPersent( "(" + cpuaverage +"% 사용중)" );
            resourceView.SetEosCpuMs( String.format( "%.2f ms", userInfoStorage.usecpu ) );
            float cputtmp =  userInfoStorage.totalcpu  * 0.01f;

            if( orientation == Configuration.ORIENTATION_LANDSCAPE ){
                resourceView.SetEosCpuSec(String.format( "%.4f sec ", cputtmp  ) +"\n"+ "(" + userInfoStorage.cputoeos + ")" );
            } else {
                resourceView.SetEosCpuSec(String.format( "%.4f sec ", cputtmp  ) + "(" + userInfoStorage.cputoeos + ")" );
            }

//

//            resourceView.SetCpuProgress(  (int)userInfoStorage.totalcpu + 1 );
//            CpuProgressAni.setProgress( (int)userInfoStorage.usecpu  + 1);
            resourceView.SetCpuProgress(  (int)userInfoStorage.totalcpu + 1);
            CpuProgressAni.setProgress( (int)userInfoStorage.usecpu  + 1 );

//            float bandwidthweight = userInfoStorage.cputoeos * 0.0001f;

            float netavernum = userInfoStorage.useband / userInfoStorage.totalband * 100.0f;
            String netaverage = String.format( "%d", (int)netavernum );

            resourceView.SetBandPersent( "(" + netaverage +"% 사용중)" );
            resourceView.SetBandMs( String.format( "%f KB", userInfoStorage.useband ) );

            float bandttmp =  userInfoStorage.totalband  * 0.01f;

            if( orientation == Configuration.ORIENTATION_LANDSCAPE ){
                resourceView.SetBandSec( String.format( "%.2f KB ", bandttmp ) + "\n" +"(" + userInfoStorage.bandtoeos + ")" );
            } else {
                resourceView.SetBandSec( String.format( "%.2f KB ", bandttmp ) + "(" + userInfoStorage.bandtoeos + ")" );
            }


            resourceView.SetBandProgress( (int)userInfoStorage.totalband );
            BandProgressAni.setProgress( (int)userInfoStorage.useband );
//            resourceView.SetBandProgress( 100 );
//            BandProgressAni.setProgress( 80 );

        } else {

            BandProgressAni.ResetProgress();
            BandProgressAni.reset();
            BandProgressAni.setProgress( (int)userInfoStorage.useband );
            BandProgressAni.start();

            CpuProgressAni.ResetProgress();
            CpuProgressAni.reset();
            CpuProgressAni.setProgress( (int)userInfoStorage.usecpu );
            CpuProgressAni.start();

        }

    }

    public void SetTransactionData( String _transid ){

    }

    public View GetMenuView(){
        return menuView.GetView();
    }

    public View GetCategoryView(){

        return  catelistview.GetView();
    }

    public View GetTradeShopView(){
        return tradeview.GetView();
    }

    public View GetEosResourceView() {
        return resourceView.GetView();
    }

    public View GetEosCpuStakeView() { return  eosCpuStakeView.GetView(); }

    public View GetEosCpuUnStakeView() { return  eosCpuUnStakeView.GetView(); }

    public View GetRamBuyView() { return  eosRamBuyView.GetView(); }
    public View GetRamSellView() { return  eosRamSellView.GetView(); }

}
