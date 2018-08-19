
package com.example.tin.pulselive.models;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentItemResponse implements Parcelable {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    public final static Creator<ContentItemResponse> CREATOR = new Creator<ContentItemResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ContentItemResponse createFromParcel(Parcel in) {
            return new ContentItemResponse(in);
        }

        public ContentItemResponse[] newArray(int size) {
            return (new ContentItemResponse[size]);
        }

    };

    protected ContentItemResponse(Parcel in) {
        in.readList(this.items, (com.example.tin.pulselive.models.Item.class.getClassLoader()));
    }

    public ContentItemResponse() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "ContentItem{" +
                "items=" + items +
                '}';
    }
}
