
package com.example.tin.pulselive.models.content_item;

import java.util.ArrayList;
import java.util.List;

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
