package com.example.tin.pulselive.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.tin.pulselive.ApiMethods;
import com.example.tin.pulselive.RestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    private String TAG = ApiModule.class.getSimpleName();
    private String apiURL;

    public ApiModule(String apiURL) {

        this.apiURL = apiURL;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttp() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {

        return new GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    @Singleton
    public ApiMethods provideApiMethods(OkHttpClient okHttpClient, Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(apiURL)
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiMethods.class);
    }

    @Provides
    @Singleton
    public RestService provideRestService(ApiMethods apiMethods) {

        return new RestService(apiMethods);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPref(Application app) {

        return app.getSharedPreferences("name", Context.MODE_PRIVATE);

    }
}
