package com.itamgames.itamsdk.ui.buy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.itamgames.itamsdk.R;
import com.itamgames.itamsdk.ui.base.BaseActivity;
import com.itamgames.itamsdk.util.fingerprint.FingerPrintHandler;
import com.itamgames.itamsdk.util.fingerprint.FingerPrintUtil;
import com.itamgames.itamsdk.util.fingerprint.IAuthenticateListener;

public class ItemInfoDlg extends BaseActivity implements IAuthenticateListener  {

    RelativeLayout info_layout = null;
    ItemInfoDlgView iteminfoview = null;

    private SharedPreferences mPreferences;
    public static final String KEY_PASSWORD = "KEY_PASSWORD";
    private FingerPrintHandler mFingerprintHandler;

    private Messenger mServiceMessenger = null;
    private boolean mIsBound;

    Animation fade_in = null;
    Animation fade_out = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        info_layout = (RelativeLayout)findViewById( R.id.ITEM_INFO_LAYOUT );
        iteminfoview = new ItemInfoDlgView( con );

        info_layout.addView( iteminfoview.GetView() );

        iteminfoview.setOnClickListener( this );

        mPreferences = PreferenceManager.getDefaultSharedPreferences(con);

        fade_in = AnimationUtils.loadAnimation( con, R.anim.fade_in );
        fade_out = AnimationUtils.loadAnimation( con, R.anim.fade_out );

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        int i = v.getId();
        if (i == R.id.TRADE_SHOP_ITEM_DLG_CLOSE_BTN) {

        } else if( i == R.id.TRADE_SHOP_ITEM_DLG_BUY_BTN  ){
            iteminfoview.ShowFingerLayout( fade_out, fade_in );
            mPreferences.edit().putString(KEY_PASSWORD, "1234qwer" ).apply();
            initSensor();
        } else if( i == R.id.ITEM_BUY_FIN_ACCEPT_BTN  ){

        }
    }

    class FingerAnimation implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

//            if( WALLSET_STATE == StatusStorage.EOS_TRANS_VIEW_STATE ){
//
//                if( tokenTransdlg != null ){
//                    tokenTransdlg.dismiss();
//                    tokenTransdlg = null;
//                }
//
//                messageHandler.sendEmptyMessage( StatusStorage.EOS_REQUEST_ABI_JSON_STATE );
//
//            }  else if( WALLSET_STATE == StatusStorage.EOS_CPU_STAKE_VIEW_STATE || WALLSET_STATE == StatusStorage.EOS_CPU_UNSTAKE_VIEW_STATE ) {
//
//                if( cpuTransdlg != null ){
//                    cpuTransdlg.dismiss();
//                    cpuTransdlg = null;
//                }
//
//                messageHandler.sendEmptyMessage( StatusStorage.EOS_REQUEST_ABI_JSON_STATE );
//            } else if( WALLSET_STATE == StatusStorage.EOS_RAM_BUY_VIEW_STATE || WALLSET_STATE == StatusStorage.EOS_RAM_SELL_VIEW_STATE ) {
//
//                if( ramTransdlg != null ){
//                    ramTransdlg.dismiss();
//                    ramTransdlg = null;
//                }
//
//                messageHandler.sendEmptyMessage( StatusStorage.EOS_REQUEST_ABI_JSON_STATE );
//            }
//            RequestAccountList( publicKey.toString() );
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void initSensor() {
        if (FingerPrintUtil.checkSensorState(con )) {
            FingerprintManager.CryptoObject cryptoObject = FingerPrintUtil.getCryptoObject();
            if (cryptoObject != null) {
//                Toast.makeText(con, "Use fingerprint to login", Toast.LENGTH_LONG).show();
                FingerprintManager fingerprintManager = (FingerprintManager)con.getSystemService(con.FINGERPRINT_SERVICE);
                mFingerprintHandler = new FingerPrintHandler(con, mPreferences, this);
                mFingerprintHandler.startAuth(fingerprintManager, cryptoObject);
            }
        } else {/// 지문기능이 없을

        }
    }

    @Override
    public void onAuthenticate(String decryptPassword) {
        showLoading();
        iteminfoview.SuccessFingerImage( fade_in );
        setStartService();
    }


    /** 서비스 시작 및 Messenger 전달 */
    private void setStartService() {
//        startService(new Intent(main.this, ControlService.class));
//        bindService(new Intent(this, ControlService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
        Log.e( "famous TEST", "recvbtn" );
//                Intent intent = new Intent("com.itamgames.itamapp.signdata.request" ) ;

//                intent.putExtra( "eos" , "0.0001");
//                intent.putExtra( "name" , "sword +1");
//                intent.putExtra( "account" , "itamnetwork2");

        Intent intent = new Intent();
        ComponentName cn = new ComponentName("com.itamgames.itamapp",
                "com.itamgames.itamapp.service.InAppService");
        intent.setComponent(cn);


//                Toast.makeText(con, "BoundService에게 요청", Toast.LENGTH_SHORT).show();
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }
    /** 서비스 정지 */
    private void setStopService() {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
//        stopService(new Intent(main.this, ControlService.class));
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("test","onServiceConnected");
            mServiceMessenger = new Messenger(iBinder);
            Bundle bundle = new Bundle();
            bundle.putString("eos" , "0.0001 EOS");
            bundle.putString("name" , "유현의 쌍검");
            bundle.putString("account" , "itamnetwork2");

            try {
                Message msg = Message.obtain(null, 1);
                msg.replyTo = mMessenger;
                msg.obj = bundle;
                mServiceMessenger.send(msg);
            }
            catch (RemoteException e) {
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
            Log.e("test","act : what " + msg.what);
            switch (msg.what) {
                case 4:
                    String value1 = msg.getData().getString("transid");
                    String value2 = msg.getData().getString("result");
                    Log.e("famous TEST","act : value1 "+value1);
                    Log.e("fasmous TEST","act : value2 "+value2);
                    String tmp = "";
                    tmp = "result : " + value2 + "\n" + "transaction id : " + value1;


                    setStopService();
                    hideLoading();
                    finish();

                    Toast.makeText(con, "구매가 완료되었습니다", Toast.LENGTH_SHORT).show();

                    break;
            }
            return false;
        }
    }));
}


//https://firebasestorage.googleapis.com/v0/b/itamapp-697be.appspot.com/o/apk%2FArenaGS_20181008.apk?alt=media&token=4a2a1419-8934-4605-bc0e-95c4718ceaa2
//https://firebasestorage.googleapis.com/v0/b/itamapp-697be.appspot.com/o/apk%2FArenaGS_20181008.apk?alt=media&token=4a2a1419-8934-4605-bc0e-95c4718ceaa2