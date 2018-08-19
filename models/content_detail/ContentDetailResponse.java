
package com.example.tin.pulselive.models.content_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentDetailResponse {

    @SerializedName("item")
    @Expose
    private DetailItem detailItem;

    public DetailItem getDetailItem() {
        return detailItem;
    }

    @Override
    public String toString() {
        return "ContentDetailResponse{" +
                "detailItem=" + detailItem +
                '}';
    }
}
