package com.example.apinstagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("44eu7Do3vIXbe9mlVEEQTl5DXTV5iopQRtrI4h94")
                // if defined
                .clientKey("WNHxm8AvmAy1fYISGvYkp39xXoidfr52VYCalW6T")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
