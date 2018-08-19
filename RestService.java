package com.example.tin.pulselive;

import com.example.tin.pulselive.models.ContentItemResponse;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Tin on 19/08/2018.
 */

public class RestService {

    private static final String TAG = RestService.class.getSimpleName();

    private ApiMethods apiMethods;

    public RestService(ApiMethods apiMethods) {

        this.apiMethods = apiMethods;
    }

    public Observable<ArrayList<ContentItemResponse>> getContentList() {
        /* Here we receive the Response, (which is already parsed when it arrives here) */
        return apiMethods.getContentList();
    }
}
