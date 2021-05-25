package com.example.viktorina;

import java.io.Serializable;

public class Categories implements Serializable {
    private final long id;
    private final String category;

    public Categories(long id, String category) {
        this.id = id;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
