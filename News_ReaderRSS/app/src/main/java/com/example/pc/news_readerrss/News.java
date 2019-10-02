package com.example.pc.news_readerrss;

/**
 * Created by PC on 30-Jan-18.
 */

public class News {

    private  String title;
    private  String description;
    private String  pubdates;
    private String image;

    public News(String title, String description, String pubdates, String image) {
        this.title = title;
        this.description = description;
        this.pubdates = pubdates;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdates() {
        return pubdates;
    }

    public void setPubdates(String pubdates) {
        this.pubdates = pubdates;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
