package com.itamgames.itamsdk.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.itamgames.itamsdk.data.ItemInfoStorage;
import com.itamgames.itamsdk.data.TransInfoStorage;
import com.itamgames.itamsdk.ui.base.ItamInAppListener;

public class ItamInappHandler  {

    Context context = null;
    private Messenger mServiceMessenger = null;
    private boolean mIsBound;

    private ItemInfoStorage itemInfo = null;
    private TransInfoStorage transInfo = null;

    private ItamInAppListener mListener;

    int RequestType = 0;

    public ItamInappHandler(Context con, ItemInfoStorage _iteminfo,  ItamInAppListener listener ) {

        context = con;
        mListener = listener;
        itemInfo = _iteminfo;

        RequestType = 0;
    }

    public ItamInappHandler(Context con, TransInfoStorage _transinfo, ItamInAppListener listener ) {

        context = con;
        mListener = listener;
        transInfo = _transinfo;

        RequestType = _transinfo.transaction_type;

    }

    public void StartTransationSerivce(){
        setStartService();
    }

    /** 서비스 시작 및 Messenger 전달 */
    private void setStartService() {

        mIsBound = true;
        Log.e( "famous TEST", "recvbtn" );


        Intent intent = new Intent();
        ComponentName cn = new ComponentName("com.itamgames.itamapp",
                "com.itamgames.itamapp.service.InAppService");
        intent.setComponent(cn);


        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }
    /** 서비스 정지 */
    private void setStopService() {
        if (mIsBound) {
            context.unbindService(mConnection);
            mIsBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Log.e("famous test","onServiceConnected");

            mServiceMessenger = new Messenger(iBinder);

            Bundle bundle = new Bundle();

            if( RequestType == 0 ){
                bundle.putString("eos" ,itemInfo.price);
                bundle.putString("name" , itemInfo.title + " " + itemInfo.memo );
                bundle.putString("account" , "itamgameusra");

                try {
                    Message msg = Message.obtain(null, 1000);
                    msg.replyTo = mMessenger;
                    msg.obj = bundle;
                    mServiceMessenger.send(msg);
                }
                catch (RemoteException e) {
                }
            } else if( RequestType == 1 || RequestType == 2 ){ // cpu
                bundle.putString("account" , transInfo.sender );
                bundle.putString("recviver" , transInfo.recver );
                bundle.putString("cpucount" , transInfo.cpuquantity );
                bundle.putString("bandcount" , transInfo.netquantity );
                bundle.putInt("staketype" , transInfo.staketransfer );
                bundle.putInt("transtype" , transInfo.transaction_type );

                try {
                    Message msg = Message.obtain(null, 1001);
                    msg.replyTo = mMessenger;
                    msg.obj = bundle;
                    mServiceMessenger.send(msg);
                }
                catch (RemoteException e) {

                }
            } else if( RequestType == 3 || RequestType == 4 ){ //  ram

                Log.e( "famous TEST" , "transInfo.token : " + transInfo.token );
                bundle.putString("account" , transInfo.sender );
                bundle.putString("recviver" , transInfo.recver );
                bundle.putString("ramcount" , transInfo.token );
                bundle.putInt("ramtype" , transInfo.staketransfer );
                bundle.putInt("transtype" , transInfo.transaction_type );

                try {
                    Message msg = Message.obtain(null, 1002);
                    msg.replyTo = mMessenger;
                    msg.obj = bundle;
                    mServiceMessenger.send(msg);
                }
                catch (RemoteException e) {

                }
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            Log.e( "famous TEST", "onServiceDisconnected : " + componentName.getClassName() );
        }
    };

    /** Service 로 부터 message를 받음 */
    private final Messenger mMessenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.e("famous test","act : what " + msg.what);
            switch (msg.what) {

                case 4:{
                    String value1 = msg.getData().getString("transid");
                    String value2 = msg.getData().getString("result");
                    Log.e("famous TEST","act : value1 "+value1);
                    Log.e("fasmous TEST","act : value2 "+value2);
//                    String tmp = "";
//                    tmp = "result : " + value2 + "\n" + "transaction id : " + value1;
                    mListener.onResponseProduct(  value2 );

                    setStopService();
                }
                break;
                case 5:{
                    String value1 = msg.getData().getString("transid");
                    String value2 = msg.getData().getString("result");
                    String value3 = msg.getData().getString("memo");
                    Log.e("famous TEST","act : value1 "+value1);
                    Log.e("fasmous TEST","act : value2 "+value2);
                    Log.e("fasmous TEST","act : value3 "+value3);
//                    String tmp = "";
//                    tmp = "result : " + value2 + "\n" + "transaction id : " + value1;
                    mListener.onResponseProduct(  value1+"|" + value2+"|" + value3 );

                    setStopService();
                }
                break;
            }
            return false;
        }
    }));
}
