package com.itamgames.itamsdk.ui.base;

public interface ItamInAppListener {

    /*
    * 유효한 앱인지 체크
    * result : json
    * */
    void onAuthApplicataion(String authResult );

    /*
     * 결제 상품 요청
     * result : json
     * */
    void onResponseProduct( String product );

    /*
     * 결제 요청
     * result : json
     * */
    void onResponsePayment( String result );
}
