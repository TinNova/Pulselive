package com.example.tin.pulselive.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tin.pulselive.AppClass;
import com.example.tin.pulselive.rest.RestService;
import com.example.tin.pulselive.models.content_detail.ContentDetailResponse;
import com.example.tin.pulselive.models.content_detail.DetailItem;
import com.example.tin.pulselive.utils.StateOfLoading;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING;
import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING_COMPLETE;
import static com.example.tin.pulselive.utils.StateOfLoading.stateCodes.LOADING_ERROR;


public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = DetailViewModel.class.getSimpleName();

    private MutableLiveData<DetailItem> contentDetailLiveData;
    private MutableLiveData<StateOfLoading.stateCodes> statesLiveData;


    @Inject
    RestService restService;

    public DetailViewModel(@NonNull Application application) {
        super(application);

        ((AppClass) application).getAndroidComponent().inject(this);

    }

    public MutableLiveData<DetailItem> listenToDataChanges(int itemId) {

        if (contentDetailLiveData == null) {

            contentDetailLiveData = new MutableLiveData<>();

            loadItems(itemId);
        }
        return contentDetailLiveData;
    }

    // Create a MutableLiveData that will be used to send messages to Activity, the Activity will have a switch statement to instruct it on what to do with each message.
    public MutableLiveData<StateOfLoading.stateCodes> listenToStatesChanges(int itemId) {

        if (statesLiveData == null) {

            statesLiveData = new MutableLiveData<>();

            loadItems(itemId);
        }
        return statesLiveData;
    }

    private void loadItems(int itemId) {


        statesLiveData.postValue(new StateOfLoading.stateCodes(LOADING, "loading"));

        restService.getContentDetail(itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContentDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ContentDetailResponse contentDetailResponse) {

                        contentDetailLiveData.postValue(contentDetailResponse.getDetailItem());
                        statesLiveData.postValue(new StateOfLoading.stateCodes(LOADING_COMPLETE, "loadingComplete"));


                        Log.d(TAG, "onNext contentDetailResponse: " + contentDetailResponse.getDetailItem().getBody());

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
