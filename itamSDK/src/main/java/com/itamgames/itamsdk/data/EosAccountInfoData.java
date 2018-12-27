package com.itamgames.itamsdk.data;

import com.itamgames.itamsdk.data.AccountInfo.Accounts;
import com.itamgames.itamsdk.data.AccountInfo.EosPermissions;
import com.itamgames.itamsdk.data.AccountInfo.Keys;
import com.itamgames.itamsdk.data.AccountInfo.Producers;
import com.itamgames.itamsdk.data.AccountInfo.Waits;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EosAccountInfoData {

    public AccountInfoStorage AccountInfoJsonParser( JSONObject jsondata ){

        AccountInfoStorage accountInfoStorage = new AccountInfoStorage();
        try {

            accountInfoStorage.AccoutName = jsondata.getString( "account_name" );
            accountInfoStorage.HeadBlockNum = jsondata.getInt( "head_block_num" );
            accountInfoStorage.HeadBlockTime = jsondata.getString( "head_block_time" );
            accountInfoStorage.Privileged = jsondata.getBoolean( "privileged" );
            accountInfoStorage.LastCodeUpdate = jsondata.getString( "last_code_update" );
            accountInfoStorage.Created = jsondata.getString( "created" );
            if( jsondata.isNull( "core_liquid_balance" ) ==  false ){
                accountInfoStorage.coreliquidbalance = jsondata.getString( "core_liquid_balance" );
            } else {
                accountInfoStorage.coreliquidbalance = "0.000 EOS";
            }

            accountInfoStorage.RamQuota = jsondata.getInt( "ram_quota" );
            accountInfoStorage.NetWeight = jsondata.getInt( "net_weight" );
            accountInfoStorage.CpuWeight = jsondata.getInt( "cpu_weight" );

            JSONObject nLimitJson = jsondata.getJSONObject( "net_limit" );

            accountInfoStorage.Netlimt.Used = nLimitJson.getInt( "used");
            accountInfoStorage.Netlimt.Available = nLimitJson.getInt( "available");
            accountInfoStorage.Netlimt.Max = nLimitJson.getInt( "max");

            JSONObject cLimitJson = jsondata.getJSONObject( "cpu_limit" );

            accountInfoStorage.Cpulimt.Used = cLimitJson.getInt( "used");
            accountInfoStorage.Cpulimt.Available = cLimitJson.getInt( "available");
            accountInfoStorage.Cpulimt.Max = cLimitJson.getInt( "max");

            accountInfoStorage.RamUsage = jsondata.getInt( "ram_usage" );

            JSONArray permissionArray =  jsondata.getJSONArray( "permissions" );

            for(int i=0; i < permissionArray.length(); i++){

                EosPermissions pBuffer = new EosPermissions();

                JSONObject pJObject = permissionArray.getJSONObject(i);  // JSONObject 추출

                pBuffer.PermName = pJObject.getString( "perm_name" );
                pBuffer.Parent = pJObject.getString( "parent" );

                JSONObject requiredAuth = pJObject.getJSONObject( "required_auth" );
                pBuffer.requiredAuths.Threshold =  requiredAuth.getInt( "threshold" );

                JSONArray rKeys = requiredAuth.getJSONArray( "keys" );

                for( int kj = 0; kj < rKeys.length(); kj++ ){
                    JSONObject rJObject = rKeys.getJSONObject(kj);  // JSONObject 추출

                    Keys kBuffer = new Keys();
                    kBuffer.key = rJObject.getString("key");
                    kBuffer.weight = rJObject.getInt( "weight");

                    pBuffer.requiredAuths.keys.add( kBuffer );
                }

                JSONArray raccount = requiredAuth.getJSONArray( "accounts" );

                for( int aj = 0; aj < raccount.length(); aj++ ){
                    JSONObject rJObject = raccount.getJSONObject(aj);  // JSONObject 추출

                    Accounts aBuffer = new Accounts();
                    aBuffer.key = rJObject.getString("key");
                    aBuffer.weight = rJObject.getInt( "weight");

                    pBuffer.requiredAuths.accounts.add( aBuffer );
                }

                JSONArray rwaits = requiredAuth.getJSONArray( "waits" );

                for( int aj = 0; aj < rwaits.length(); aj++ ){
                    JSONObject rJObject = rwaits.getJSONObject(aj);  // JSONObject 추출

                    Waits wBuffer = new Waits();
                    wBuffer.key = rJObject.getString("key");
                    wBuffer.weight = rJObject.getInt( "weight");

                    pBuffer.requiredAuths.waits.add( wBuffer );
                }

                accountInfoStorage.permissions.add( pBuffer );
            }


            JSONObject totaljson = jsondata.getJSONObject( "total_resources"  );
            accountInfoStorage.totalResources.Owner = totaljson.getString( "owner" );
            accountInfoStorage.totalResources.NetWeight = totaljson.getString( "net_weight" );
            accountInfoStorage.totalResources.CpuWeight = totaljson.getString( "cpu_weight" );
            accountInfoStorage.totalResources.RamBytes = totaljson.getInt( "ram_bytes" );




            if( jsondata.isNull( "self_delegated_bandwidth" ) ==  false ){
                JSONObject selfjson = jsondata.getJSONObject( "self_delegated_bandwidth"  );
                accountInfoStorage.selfDelegated.from = selfjson.getString( "from" );
                accountInfoStorage.selfDelegated.to = selfjson.getString( "to" );
                accountInfoStorage.selfDelegated.NetWeight = selfjson.getString( "net_weight" );
                accountInfoStorage.selfDelegated.CpuWeight = selfjson.getString( "cpu_weight" );


            }

            if( jsondata.isNull( "refund_request" ) ==  false ){

                JSONObject refundJson = jsondata.getJSONObject( "refund_request" );
                accountInfoStorage.refund_net_amount = refundJson.getString( "net_amount");
                accountInfoStorage.refund_cpu_amount = refundJson.getString( "cpu_amount");
            }

            if( jsondata.isNull( "voter_info" ) ==  false ){

                JSONObject voterJson = jsondata.getJSONObject( "voter_info" );

                accountInfoStorage.voterInfo.Owner = voterJson.getString( "owner" );
                accountInfoStorage.voterInfo.Proxy = voterJson.getString( "proxy" );
                accountInfoStorage.voterInfo.staked = voterJson.getInt( "staked" );
                accountInfoStorage.voterInfo.LastVoteWeight = voterJson.getString( "last_vote_weight" );
                accountInfoStorage.voterInfo.ProxiedVoteWeight = voterJson.getString( "proxied_vote_weight" );
                accountInfoStorage.voterInfo.IsProxy = voterJson.getInt( "is_proxy" );

                JSONArray vproducers = voterJson.getJSONArray( "producers" );
                for( int vi =  0; vi < vproducers.length(); vi++ ){

                    Producers pBuffer = new Producers();

                    accountInfoStorage.voterInfo.producers.add( pBuffer );


                }
            }

        } catch ( JSONException e ){
            return null;
        }
        return accountInfoStorage;
    }
}
