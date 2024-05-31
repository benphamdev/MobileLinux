package com.example.uploadimageusingretrofit;

import java.io.Serializable;
import java.util.List;

public class PostCreationRequest implements Serializable {
    private final String name;
    private final String content;
    private final List<Integer> listTagId;

    public PostCreationRequest(String name, String content, List<Integer> listTagId) {
        this.name = name;
        this.content = content;
        this.listTagId = listTagId;
    }
}
