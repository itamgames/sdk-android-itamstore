package com.itamgames.itamsdk.data;


import com.itamgames.itamsdk.data.AccountInfo.EosPermissions;
import com.itamgames.itamsdk.data.AccountInfo.LimitInfo;
import com.itamgames.itamsdk.data.AccountInfo.SelfDelegated;
import com.itamgames.itamsdk.data.AccountInfo.TotalResources;
import com.itamgames.itamsdk.data.AccountInfo.Voterinfo;

import java.util.ArrayList;

public class AccountInfoStorage {

    public String AccoutName = "";
    public int HeadBlockNum = -1;
    public String HeadBlockTime = "";
    public Boolean Privileged = false;
    public String LastCodeUpdate = "";
    public String Created = "";
    public int RamQuota = -1;
    public int NetWeight = -1;
    public int CpuWeight = -1;

    public String coreliquidbalance = "";

    public String eosdaler = "";

    public int RamUsage = -1;

    public String RefundRequest = "";

    public LimitInfo Netlimt = new LimitInfo();
    public LimitInfo Cpulimt = new LimitInfo();

    public ArrayList<EosPermissions> permissions = new ArrayList<>();

    public TotalResources totalResources = new TotalResources();

    public SelfDelegated selfDelegated = new SelfDelegated();

    public String refund_net_amount = "";
    public String refund_cpu_amount = "";


    public Voterinfo voterInfo = new Voterinfo();


}
