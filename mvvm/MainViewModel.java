package com.example.tin.pulselive.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tin.pulselive.AppClass;
import com.example.tin.pulselive.rest.RestService;
import com.example.tin.pulselive.models.content_item.ContentItemResponse;
import com.example.tin.pulselive.models.content_item.Item;
import com.example.tin.pulselive.utils.StateOfLoading;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING;
import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING_COMPLETE;
import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING_ERROR;


public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private MutableLiveData<ArrayList<Item>> contentLiveData;
    private MutableLiveData<StateOfLoading.stateCodes> statesLiveData;


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

    // Create a MutableLiveData that will be used to send messages to Activity, the Activity will have a switch statement to instruct it on what to do with each message.
    public MutableLiveData<StateOfLoading.stateCodes> listenToStatesChanges() {

        if (statesLiveData == null) {

            statesLiveData = new MutableLiveData<>();

            loadItems();
        }
        return statesLiveData;
    }

    public void loadItems() {


        statesLiveData.postValue(new StateOfLoading.stateCodes(LOADING, "loading"));

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
                        statesLiveData.postValue(new StateOfLoading.stateCodes(LOADING_COMPLETE, "loadingComplete"));

                    }

                    @Override
                    public void onError(Throwable e) {

                        statesLiveData.postValue(new StateOfLoading.stateCodes(LOADING_ERROR, "loadingFailed"));

                        Log.e("MainViewModel", "onError: error while load listings " + Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
