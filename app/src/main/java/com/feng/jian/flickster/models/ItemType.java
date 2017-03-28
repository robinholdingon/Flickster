package com.feng.jian.flickster.models;

import java.sql.Types;

/**
 * Created by jian_feng on 3/27/17.
 */

public class ItemType {
    public enum TYPE {
        POPULAR,
        REGULAR
    }

    public ItemType(String label, Types type) {
        this.label = label;
        this.type = type;
    }

    public String label;
    public Types type;
}
