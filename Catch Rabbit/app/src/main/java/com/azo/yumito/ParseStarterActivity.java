package com.azo.yumito;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("SUftrH07BWQ4kjufiEXc1LpPI11pWnyah6AMTuBg")
                .clientKey("WRGFLqwxfDJ7xTUkaGeRS0n90etWx67laMLGnTWw")
                .server("https://parseapi.back4app.com/")
                .build()


        );
    }
}
