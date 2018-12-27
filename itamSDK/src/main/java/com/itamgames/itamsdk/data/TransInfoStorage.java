package com.itamgames.itamsdk.data;

public class TransInfoStorage {

    /*TODO 트렌젝션 데이터*/

    public int transaction_type = 0;
    public String code = "";
    public String action = "";
    public String sender = "";
    public String recver = "";
    public String token = "";
    public String memo = "";

    public int staketransfer = 0; // 0 대여 1 주는거 자신한테는 무조건 0
    /**/
    public String netquantity = "";
    public String cpuquantity = "";

    public void reset(){
        transaction_type = -1;
        code = "";
        action = "";
        sender = "";
        recver = "";
        token = "";
        memo = "";

        staketransfer = 0; // 0 대여 1 주는거 자신한테는 무조건 0
        /**/
        netquantity = "";
        cpuquantity = "";
    }

}
