package com.banking.thejavabanking.models.entity;

import com.example.uploadimageusingretrofit.Photo;
import com.example.uploadimageusingretrofit.Tag;

import java.util.ArrayList;
import java.util.List;

public class Post {
    String name;
    String content;
    Long likeCount;
    Long viewCount;
    List<Tag> tags = new ArrayList<>();
    Photo photo;

    public Post(
            String name, String content, Long likeCount, Long viewCount, List<Tag> tags, Photo photo
    ) {
        this.name = name;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.tags = tags;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Photo getPhoto() {
        return photo;
    }
}
