package com.itamgames.itamsdk.data.AccountInfo;

import java.util.ArrayList;

public class RequiredAuth {

    public int Threshold = -1;
    public ArrayList<Keys> keys = new ArrayList<>();
    public ArrayList<Accounts> accounts = new ArrayList<>();
    public ArrayList<Waits> waits = new ArrayList<>();
}
