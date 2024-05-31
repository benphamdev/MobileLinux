package com.example.uploadimageusingretrofit;

import java.util.List;

public class PostResponse {

        Integer id;
        String name;
        String content;
        Long likeCount;
        Long viewCount;
        String thumbnail;
        List<Tag> tags;

    public PostResponse(
            Integer id, String name, String content, Long likeCount, Long viewCount,
            String thumbnail,
            List<Tag> tags
    ) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.thumbnail = thumbnail;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
