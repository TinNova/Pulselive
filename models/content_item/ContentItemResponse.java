
package com.example.tin.pulselive.models.content_item;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentItemResponse {

    @SerializedName("items")
    @Expose
    private final ArrayList<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ContentItem{" +
                "items=" + items +
                '}';
    }
}
