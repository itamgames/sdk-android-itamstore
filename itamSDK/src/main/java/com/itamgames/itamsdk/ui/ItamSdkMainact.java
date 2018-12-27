package com.itamgames.itamsdk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.itamgames.itamsdk.PreferenceHelper;
import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.data.AccountInfoStorage;
import com.itamgames.itamsdk.data.EosAccountInfoData;
import com.itamgames.itamsdk.data.EosDataCalculator;
import com.itamgames.itamsdk.data.TransInfoStorage;
import com.itamgames.itamsdk.network.RpcRequest;
import com.itamgames.itamsdk.ui.base.BaseActivity;
import com.itamgames.itamsdk.ui.base.ItamInAppListener;
import com.itamgames.itamsdk.ui.buy.ItemInfoDlg;
import com.itamgames.itamsdk.ui.dialog.Transactiondialog;
import com.itamgames.itamsdk.util.ItamInappHandler;

import org.json.JSONObject;

public class ItamSdkMainact extends BaseActivity {

    RelativeLayout menu_layout = null;
    RelativeLayout category_layout = null;
    RelativeLayout trade_shop_layout = null;

    Button close_btn = null;

    ItamSdkPresenter presenter;

    PreferenceHelper preference;

    AccountInfoStorage accountInfo;

    EosAccountInfoData eosAccountInfoData;

    String accountname = null;
    String publickey = null;
    String itamtoken = null;

    ItamInappHandler itamInappHandler;

    int transtype = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menu_layout = (RelativeLayout)findViewById( R.id.MENU_LAYOUT );
        category_layout = (RelativeLayout)findViewById( R.id.CATEGORY_LAYOUT );
        trade_shop_layout = (RelativeLayout)findViewById( R.id.TRADE_SHOP_LAYOUT );

        close_btn = (Button)findViewById( R.id.SDK_CLOSE_BTN );

        messageHandler = new Handler( new IncomingHandlerCallback() );

        presenter = new ItamSdkPresenter( con, messageHandler );

        accountInfo = new AccountInfoStorage();
        eosAccountInfoData = new EosAccountInfoData();

        preference = new PreferenceHelper(con );


        presenter.userInfoStorage = preference.LoadUserInfo( con );


        Toast.makeText( con, "userInfoStorage : " + presenter.userInfoStorage.userAccount, Toast.LENGTH_SHORT).show();

        Intent i = getIntent();

//        accountname =  i.getStringExtra( "accountname" );
//        publickey =  i.getStringExtra( "publickey" );
//        itamtoken =  i.getStringExtra( "itamtoken" );

        /*TEST*/
        if( accountname == null ){

            accountname = "itamgameusra";
            itamtoken = "1000.0000";
            publickey = "EOS7WbcbgYszYK4K71C4nGohZmrNRQnKEx412GKUHTxtf5QRKBCFX";

        } else {
            if( accountname.length() <= 0 ){

                if( presenter.userInfoStorage.userAccount.length() <= 0 ){

                    Toast.makeText( con, "Itam Store 인증을해주세요", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
            } else {

                presenter.userInfoStorage.userAccount = accountname;
                presenter.userInfoStorage.userPubkey = publickey;
                presenter.userInfoStorage.itamtoken = itamtoken;

                preference.SetAccountName( accountname );
                preference.SetPublicKey( publickey );
            }
        }



        Init();

        Log.e( "famous TEST" , "ItamSdkMainact accountname : " + preference.GetAccountName() );
        Log.e( "famous TEST" , "ItamSdkMainact publickey : " + preference.GetPublicKey() );

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        messageHandler.sendEmptyMessage( 3 );
    }


    void Init(){

        presenter.SetTradeShop();

        menu_layout.addView( presenter.GetMenuView() );

        category_layout.addView( presenter.GetCategoryView() );
        trade_shop_layout.addView( presenter.GetTradeShopView() );

    }

    class IncomingHandlerCallback implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {

            switch ( msg.what ){

                case 0:{

                    presenter.SetEosInfo();
                    trade_shop_layout.removeAllViews();
                    trade_shop_layout.addView( presenter.GetEosResourceView() );
                    presenter.SetCpuResource();
                }
                break;

                case 1:{
                    presenter.SetItamToken( itamtoken );
                    trade_shop_layout.removeAllViews();
                    trade_shop_layout.addView( presenter.GetTradeShopView() );

                }
                break;

                case 2:{
                    ShowItemDlg();

                }
                break;

                case 3:{
                    RequestAccountInfo();

                }
                break;

                case 4:{

                    String eoscount = EosDataCalculator.SetEos( accountInfo.coreliquidbalance,accountInfo.voterInfo.staked,
                            accountInfo.RefundRequest, accountInfo.refund_net_amount, accountInfo.refund_cpu_amount );

                    presenter.userInfoStorage.eostoken = eoscount;
                    presenter.userInfoStorage.eosstake = EosDataCalculator.SetStake(accountInfo.voterInfo.staked);
                    presenter.userInfoStorage.eosunstake = EosDataCalculator.SetUnStake(accountInfo.coreliquidbalance);

                    if( accountInfo.refund_net_amount.length() > 0  ){
                        float tmp3 = Float.parseFloat((accountInfo.refund_net_amount).split( " ")[0] );
                        tmp3 += Float.parseFloat((accountInfo.refund_cpu_amount).split( " ")[0] );
                        presenter.userInfoStorage.refund = String.format( "%.4f", tmp3 );
                    } else {
                        presenter.userInfoStorage.refund = "0.000";
                    }

                    presenter.userInfoStorage.totalram = EosDataCalculator.SetTotalRam( accountInfo.RamQuota );
                    presenter.userInfoStorage.useram = EosDataCalculator.SetTotalRam( accountInfo.RamUsage );

                    presenter.userInfoStorage.totalcpu = EosDataCalculator.SetTotalCpu( accountInfo.Cpulimt.Max );
                    presenter.userInfoStorage.usecpu = EosDataCalculator.SetUseCpu( accountInfo.Cpulimt.Used );
                    presenter.userInfoStorage.cputoeos = EosDataCalculator.SetUseCpu( accountInfo.CpuWeight );

                    presenter.userInfoStorage.totalband = EosDataCalculator.SetTotalCpu( accountInfo.Netlimt.Max );
                    presenter.userInfoStorage.useband = EosDataCalculator.SetUseCpu( accountInfo.Netlimt.Used );
                    presenter.userInfoStorage.bandtoeos = EosDataCalculator.SetUseCpu( accountInfo.NetWeight );

                    preference.SaveUserInfo( con, presenter.userInfoStorage );
                    messageHandler.sendEmptyMessage( 1 );

                }
                break;

                case 5:{

                    Toast.makeText( con, "error", Toast.LENGTH_SHORT).show();

                }
                break;

                case 6:{ /*Input UI*/

                    trade_shop_layout.removeAllViews();
                    int idx = msg.arg1;
                    if( idx == 0 ){
                        trade_shop_layout.addView( presenter.GetEosCpuStakeView() );
                    } else if( idx == 1 ){
                        trade_shop_layout.addView( presenter.GetEosCpuUnStakeView() );
                    } else if( idx == 2 ){
                        trade_shop_layout.addView( presenter.GetRamBuyView() );
                    } else if( idx == 3 ){
                        trade_shop_layout.addView( presenter.GetRamSellView() );
                    }

                }
                break;
                case 7:{
                    int idx = msg.arg1;

                    Log.e( "famous TEST" , "msg.arg1 : " + idx );
                    trade_shop_layout.removeAllViews();
                    trade_shop_layout.addView( presenter.GetEosResourceView() );
                    if( idx == 0 ){
                        presenter.resourceView.ShowCpuLayout();
                        presenter.SetCpuResource();
                    } else {
                        presenter.resourceView.ShowRamLayout();
                        presenter.SetRamResource();
                    }
                }
                break;
                case 8: { //  cpu stake 요청

                    showLoading();
                    Log.e( "famous TSET" , "RequestResourceTransfer" );
                    transtype = msg.arg1;
                    RequestResourceTransfer();

                }
                break;
                case 100:{ // 실패

                }
                break;
            }

            return false;
        }
    }

    void ShowItemDlg(){
        Intent i = new Intent( this, ItemInfoDlg.class );
        startActivityForResult( i , 0);
    }


    public void RequestAccountInfo() {

        showLoading();
        RpcRequest.Accountinfo( preference.GetAccountName(), new JSONObjectRequestListener()  {

            @Override
            public void onResponse(JSONObject response) {

                accountInfo = eosAccountInfoData.AccountInfoJsonParser( response  );
                messageHandler.sendEmptyMessage(  4 );
                hideLoading();
            }

            @Override
            public void onError(ANError anError) {
                hideLoading();
                messageHandler.sendEmptyMessage( 5 );
//                Signup_privatekey_error_text.setText( R.string.join_checkkey_error_text );
            }
        });
    }

    public void RequestResourceTransfer(){

        TransInfoStorage info = new TransInfoStorage();

        if( transtype == 1  ){ //cpu stake

            info.sender = presenter.userInfoStorage.userAccount;
            info.recver = presenter.eosCpuStakeView.GetStakeRecvierAccount();

            info.cpuquantity = presenter.eosCpuStakeView.GetCpuCount();
            info.netquantity = presenter.eosCpuStakeView.GetBandCount();

            if( presenter.eosCpuStakeView.IsTransfer() == true ){
                info.staketransfer = 1;
            } else {
                info.staketransfer = 0;
            }

            info.transaction_type = transtype;


        } else if( transtype == 2 ) { //cpu unstake

            info.sender = presenter.userInfoStorage.userAccount;
            info.recver = presenter.eosCpuUnStakeView.GetStakeRecvierAccount();

            info.cpuquantity = presenter.eosCpuUnStakeView.GetCpuCount();
            info.netquantity = presenter.eosCpuUnStakeView.GetBandCount();

            info.transaction_type = transtype;

        } else if( transtype == 3 ){ // ram buy

            info.sender = presenter.userInfoStorage.userAccount;

            info.recver = presenter.eosRamBuyView.GetRamBuyAccount();
            info.token = presenter.eosRamBuyView.GetRamCount();

            if( presenter.eosRamBuyView.GetRamType() == true ){
                info.staketransfer = 0;
            } else {
                info.staketransfer = 1;
            }

            info.transaction_type = transtype;

        } else if( transtype == 4 ){ // ram sell

            info.sender = presenter.userInfoStorage.userAccount;
            info.token = presenter.eosRamSellView.GetRamCount();

            info.transaction_type = transtype;

        }


        itamInappHandler = new ItamInappHandler(con, info, new ItamInAppListener() {

            @Override
            public void onAuthApplicataion(String authResult) {

            }

            @Override
            public void onResponseProduct(String product) {

                Log.e( "famous TES" , "product : " + product  );
                String result [] = product.split( "\\|");
                String transid = result[0];
                String resultmsg = result[1];
                String memo = result[2];

                Log.e( "famous tEST" , "transid : " +transid );
                Log.e( "famous tEST" , "resultmsg : " +resultmsg );
                Log.e( "famous tEST" , "memo : " +memo );

                if( resultmsg.matches( "success") == true ){

                    Transactiondialog transactiondlg = new Transactiondialog(con, transid );
                    transactiondlg.show();

                    hideLoading();

                    messageHandler.sendEmptyMessage( 3 );

                } else {
                    Toast.makeText(con, "실패하였습니다. " , Toast.LENGTH_SHORT ).show();
                    hideLoading();
                }

            }

            @Override
            public void onResponsePayment(String result) {

            }
        });

        itamInappHandler.StartTransationSerivce();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
