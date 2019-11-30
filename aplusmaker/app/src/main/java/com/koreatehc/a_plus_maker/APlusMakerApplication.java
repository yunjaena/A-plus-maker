package com.koreatehc.a_plus_maker;

import android.app.Application;
import android.content.Context;

public class APlusMakerApplication extends Application {
    private Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        SaveFileSharedPrefernce.getInstance().init(applicationContext);
    }
}
