package com.kjsc.myapplication;

import android.app.Application;


import androidx.multidex.MultiDexApplication;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //屏幕适配
        AutoSizeConfig.getInstance().getUnitsManager().setSupportDP(false).setSupportSubunits(Subunits.PT);
    }
}