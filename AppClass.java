package com.example.tin.pulselive;

import android.app.Application;

import com.example.tin.pulselive.dagger.AndroidComponent;
import com.example.tin.pulselive.dagger.ApiModule;
import com.example.tin.pulselive.dagger.DaggerAndroidComponent;
import com.example.tin.pulselive.utils.Const;


public class AppClass extends Application {

    private AndroidComponent androidComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        daggerInit();

        androidComponent.inject(this);
    }

    private void daggerInit() {

        androidComponent = DaggerAndroidComponent.builder()
                .apiModule(new ApiModule(Const.BASE_URL))
                .build();
    }

    public AndroidComponent getAndroidComponent() {
        return androidComponent;
    }
}
