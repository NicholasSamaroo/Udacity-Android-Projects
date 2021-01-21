package com.example.googlebooks.Models;

import java.util.List;

public class VolumeInfo {
    private String title;
    private List<String> authors;
    private String publisher;
    private String description;
    private int pageCount;
    private List<String> categories;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<String> getCategories() {
        return categories;
    }
}
