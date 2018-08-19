
package com.example.tin.pulselive.models;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentItemResponse {

    @SerializedName("items")
    @Expose
    private ArrayList<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ContentItem{" +
                "items=" + items +
                '}';
    }
}
