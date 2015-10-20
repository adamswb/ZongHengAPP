package com.utupio.zongheng.pojo;

import java.io.Serializable;

/**
 * Created by QiWangming on 2015/6/13.
 */
public class News implements Serializable {
    private String title;
    private String desc;
    private String price;
    private String disance;
    private int photoId;

    public News(String title, String desc, String disance, String price, int photoId) {
        this.title = title;
        this.desc = desc;
        this.disance = disance;
        this.price = price;
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisance() {
        return disance;
    }

    public void setDisance(String disance) {
        this.disance = disance;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
