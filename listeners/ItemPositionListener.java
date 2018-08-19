package com.example.tin.pulselive.listeners;

import com.example.tin.pulselive.models.content_item.Item;

/**
 * Created by Tin on 19/08/2018.
 */

public interface ItemPositionListener {

    /**
     * Listener now returns exact object which we need to show on details.
     * @param item
     */
    void coinItemClick(Item item);
}
