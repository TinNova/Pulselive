package com.example.tin.pulselive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tin.pulselive.models.ContentItemResponse;
import com.example.tin.pulselive.models.Item;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private MutableLiveData<ArrayList<Item>> contentLiveData;

    @Inject
    RestService restService;

    public MainViewModel(@NonNull Application application) {
        super(application);

        ((AppClass) application).getAndroidComponent().inject(this);

    }

    public MutableLiveData<ArrayList<Item>> listenToDataChanges() {

        if (contentLiveData == null) {

            contentLiveData = new MutableLiveData<>();

            loadItems();
        }
        return contentLiveData;
    }

    public void loadItems() {


//        statesLiveData.postValue(new StateOfLoading.stateCodes(0, "loading"));

        restService.getContentList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContentItemResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ContentItemResponse contentItemResponse) {

//                        mRocketResponse.clear();
//                        mRocketResponse.addAll(rocketResponse);
                        contentLiveData.postValue((ArrayList<Item>) contentItemResponse.getItems());
                        Log.d(TAG, "contentItemResponse.getItems: " + contentItemResponse.getItems());
//                        statesLiveData.postValue(new StateOfLoading.stateCodes(1, "loadingComplete"));

                    }

                    @Override
                    public void onError(Throwable e) {

//                        statesLiveData.postValue(new StateOfLoading.stateCodes(2, "loadingFailed"));

                        Log.e("MainViewModel", "onError: error while load listings " + Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
