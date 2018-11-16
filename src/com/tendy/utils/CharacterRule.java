package com.tendy.utils;

public class CharacterRule {
    private Integer index;
    private String title;
    private String content;

    public CharacterRule(String title, String content, Integer index) {
        this.index = index;
        this.title = title;
        this.content = content;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}