package com.itamgames.itamsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.itamgames.itamsdk.data.UserInfoStorage;

public class PreferenceHelper {


    private SharedPreferences mSp;

    Context context = null;

    public PreferenceHelper( Context con ) {
        mSp = PreferenceManager.getDefaultSharedPreferences(con );
    }

    public String GetAccountName(){
        return mSp.getString( "itamaccount", "" );
    }


    public void SetAccountName( String _account ){
        mSp.edit().putString("itamaccount", _account ).apply();
    }


    public String GetPublicKey(){
        return mSp.getString( "publickey", "" );
    }


    public void SetPublicKey( String _pkey ){
        mSp.edit().putString("publickey", _pkey ).apply();
    }

    public UserInfoStorage LoadUserInfo(Context c) {


        UserInfoStorage userinfo = new UserInfoStorage();

        mSp = c.getSharedPreferences("userinfostorage", Context.MODE_PRIVATE);

        if (mSp.contains("userinfostorage") == false) {

            userinfo.userAccount = mSp.getString("account", "" );
            userinfo.userPubkey = mSp.getString("publkey", "" );

            userinfo.eostoken = mSp.getString("eostoken", "" );
            userinfo.itamtoken = mSp.getString("itamtoken", "" );
            userinfo.refund = mSp.getString("refund", "" );

            userinfo.eosstake = mSp.getString("eosstake", "" );
            userinfo.eosunstake = mSp.getString("eosunstake", "" );

            userinfo.totalram = mSp.getFloat("totalram", 0.0f );
            userinfo.useram = mSp.getFloat("useram", 0.0f );

            userinfo.totalcpu = mSp.getFloat("totalcpu", 0.0f );
            userinfo.usecpu = mSp.getFloat("usecpu", 0.0f );

            userinfo.totalband = mSp.getFloat("totalband", .0f);
            userinfo.useband = mSp.getFloat("useband", .0f);

            userinfo.bandtoeos = mSp.getString("netwight", "" );;
            userinfo.cputoeos = mSp.getString("cpuwight", "" );;

        }
        return userinfo;

    }

    public void SaveUserInfo(Context c, UserInfoStorage _userinfo ) {
        mSp = c.getSharedPreferences("userinfostorage", Context.MODE_PRIVATE );
        if (mSp.contains("userinfostorage") == false) {

            SharedPreferences.Editor pe = mSp.edit();

            pe.putString( "account", _userinfo.userAccount  );
            pe.putString( "publkey", _userinfo.userPubkey );
            pe.putString( "eostoken", _userinfo.eostoken );
            pe.putString( "itamtoken", _userinfo.itamtoken );
            pe.putString( "refund", _userinfo.refund );

            pe.putString( "eosstake", _userinfo.eosstake );
            pe.putString( "eosunstake", _userinfo.eosunstake );

            pe.putString( "eostoken", _userinfo.eostoken );
            pe.putString( "itamtoken", _userinfo.itamtoken );

            pe.putFloat( "totalram", _userinfo.totalram );
            pe.putFloat( "useram", _userinfo.useram );

            pe.putFloat( "totalcpu", _userinfo.totalcpu );
            pe.putFloat( "usecpu", _userinfo.usecpu );

            pe.putFloat( "totalband", _userinfo.totalband );
            pe.putFloat( "useband", _userinfo.useband );

            pe.putString( "netwight", _userinfo.bandtoeos );
            pe.putString( "cpuwight", _userinfo.cputoeos );

            pe.commit();
        }
    }
}
