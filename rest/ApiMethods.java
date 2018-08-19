package com.example.tin.pulselive.rest;

import com.example.tin.pulselive.models.content_detail.ContentDetailResponse;
import com.example.tin.pulselive.models.content_item.ContentItemResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiMethods {

    @GET("test/native/contentList.json")
    Observable<ContentItemResponse> getContentList();


    @GET("test/native/content/{id}.json")
    Observable<ContentDetailResponse> getContentDetail(@Path("id") int itemId);

}
