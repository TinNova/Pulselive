package com.example.tin.pulselive;

import com.example.tin.pulselive.models.ContentItemResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Tin on 19/08/2018.
 */

public interface ApiMethods {

    @GET("test/native/contentList.json")
    Observable<ArrayList<ContentItemResponse>> getContentList();

}
