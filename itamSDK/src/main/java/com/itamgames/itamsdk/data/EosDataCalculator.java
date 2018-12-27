package com.itamgames.itamsdk.data;

public class EosDataCalculator {

    public static String SetStake( int _stake ) {

        String _rational = "";
        String _number = "0";

        String buf = String.format( "%d", _stake );

        if( buf.length() == 4 ){
            _rational = buf.substring( buf.length() - 4, buf.length() );
            _number = "0";
        } else if( buf.length() > 4 ) {
            _rational = buf.substring( buf.length() - 4, buf.length() );
            _number = buf.substring( 0 , buf.length() - 4 );
        } else {
            _rational = "0";
            _number = "0000";
        }



        return _number +"." + _rational;

    }

    public static String SetUnStake( String _unstake ) {

        String tmp [] = _unstake.split(" " );
        return tmp[0];

    }

//    accountInfoStorage.coreliquidbalance
    public static String EosToDaler(  String _coreliquidbalance, float _daler  ) {

        String buf1 [] = _coreliquidbalance.split( " " );

        float tmp1 = Float.parseFloat(buf1[0]);

        return  String.format( "%.4f", tmp1*_daler );
    }

    // coreliquidbalance : accountInfoStorage.coreliquidbalance
    // voterstaked : accountInfoStorage.voterInfo.stake
    // refund : accountInfoStorage.RefundRequest
    // net amount : accountInfoStorage.refund_net_amount
    // cpu amount : accountInfoStorage.refund_cpu_amount
    public static String SetEos( String _coreliquidbalance, int voterstaked , String refundrequest , String cpuamount, String ramamout ){

        String buf1 [] = _coreliquidbalance.split( " " );
        float tmp1 = .0f;
        float tmp2 = .0f;
        float tmp3 = .0f;

        tmp1 = Float.parseFloat(buf1[0]);

        if( voterstaked > 0 ){
            tmp2 = Float.parseFloat( SetStake( (voterstaked) ) );

        }

        if( refundrequest.length() > 0 ){
            tmp3 = Float.parseFloat( ( cpuamount.split( " ")[0]) );
            tmp3 += Float.parseFloat( ( ramamout).split( " ")[0] );

        }

//        float max =  (tmp1 + tmp2 + tmp3);
//
//        if (tmp1 > 0) {
//            seaked = (tmp2 / max *100 )+ 0.5f;
//        }
//
//        if (tmp2 > 0) {
//            unseaked = (tmp1 / max *100 )+ 0.5f;
//        }
//
//        if (tmp3 > 0) {
//            refund = (tmp3 / max *100 ) + 0.5f;
//        }

        return String.format( "%.4f", tmp1 + tmp2 + tmp3 );
    }

    public static float SetUselRam( int ramusage ) {

        float useram = (float)ramusage / (1024);

        return useram;//String.format("%.4f KB",  );
    }

    public static float SetTotalRam( int ramquota ) {

        float totalram = (float)ramquota / (1024);

        return totalram;
    }

    public static float SetTotalCpu( int _max ) {

        float tcpu = (float)_max * 0.01f;

        return tcpu;
    }

    public static float SetUseCpu( int _use ) {

        float useCpu = (float)_use * 0.01f;

        return useCpu;
    }

    public static float SetCpuWidht( int _cpuweight ) {

        float weight = (float)_cpuweight * 0.0001f;

        return weight;
    }


    public static float SetTotalBand( int _net ) {

        float tband = (float)_net * 0.001f;

        return tband;//String.format("%.4f KB",  );
    }

    public static float SetUseBand( int used ) {

         float useband = (float)used * 0.0f;

        return useband;
    }

    public static float SetBandWidht( int netweight ) {

        float weight = (float)netweight * 0.0001f;

        return weight;
    }

}
