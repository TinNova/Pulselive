
package com.example.tin.pulselive.models.content_detail;

import android.os.Parcel;

import com.example.tin.pulselive.models.content_item.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentDetailResponse {

    @SerializedName("item")
    @Expose
    private DetailItem detailItem;


    public ContentDetailResponse() {
    }

    public DetailItem getDetailItem() {
        return detailItem;
    }

    public void setDetailItem(DetailItem detailItem) {
        this.detailItem = detailItem;
    }

    @Override
    public String toString() {
        return "ContentDetailResponse{" +
                "detailItem=" + detailItem +
                '}';
    }
}
