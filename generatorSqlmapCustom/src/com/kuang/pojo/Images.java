package com.kuang.pojo;

public class Images {
    private Integer id;

    private String imagesUrl;

    private String imagesAlt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl == null ? null : imagesUrl.trim();
    }

    public String getImagesAlt() {
        return imagesAlt;
    }

    public void setImagesAlt(String imagesAlt) {
        this.imagesAlt = imagesAlt == null ? null : imagesAlt.trim();
    }
}