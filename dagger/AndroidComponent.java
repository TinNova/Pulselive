package com.example.tin.pulselive.dagger;

import com.example.tin.pulselive.AppClass;
import com.example.tin.pulselive.mvvm.DetailViewModel;
import com.example.tin.pulselive.mvvm.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {ApiModule.class})
@Singleton
public interface AndroidComponent {

    void inject(AppClass appClass);
    void inject(MainViewModel mainViewModel);
    void inject(DetailViewModel detailViewModel);

}


