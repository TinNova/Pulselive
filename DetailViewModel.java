package com.example.tin.pulselive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tin.pulselive.models.content_detail.ContentDetailResponse;
import com.example.tin.pulselive.models.content_detail.DetailItem;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Tin on 19/08/2018.
 */

public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = DetailViewModel.class.getSimpleName();

    private MutableLiveData<DetailItem> contentDetailLiveData;

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

    public void loadItems(int itemId) {


//        statesLiveData.postValue(new StateOfLoading.stateCodes(0, "loading"));

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

                        Log.d(TAG, "onNext contentDetailResponse: " + contentDetailResponse.getDetailItem().getBody());

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
