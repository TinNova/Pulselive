package com.example.tin.pulselive.rest;

import com.example.tin.pulselive.models.content_detail.ContentDetailResponse;
import com.example.tin.pulselive.models.content_item.ContentItemResponse;

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

    public Observable<ContentItemResponse> getContentList() {
        /* Here we receive the Response, (which is already parsed when it arrives here) */
        return apiMethods.getContentList();
    }

    public Observable<ContentDetailResponse> getContentDetail(int itemId) {
        /* Here we receive the Response, (which is already parsed when it arrives here) */
        return apiMethods.getContentDetail(itemId);
    }
}
