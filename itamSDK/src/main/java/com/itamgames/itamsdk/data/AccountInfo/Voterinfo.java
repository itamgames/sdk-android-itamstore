package com.itamgames.itamsdk.data.AccountInfo;

import java.util.ArrayList;

public class Voterinfo {

    public String Owner = "";
    public String Proxy = "";
    public String CpuWeight = "";
    public int staked = -1;
    public String LastVoteWeight = "";
    public String ProxiedVoteWeight = "";
    public int IsProxy = -1;

    public ArrayList<Producers> producers = new ArrayList<>();
}
