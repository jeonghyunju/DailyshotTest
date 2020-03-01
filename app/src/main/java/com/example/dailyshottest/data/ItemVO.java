package com.example.dailyshottest.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemVO implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("processed_image")
    @Expose
    private String processedImage;

    @SerializedName("is_new")
    @Expose
    private boolean isNew;

    public ItemVO() {
    }

    public ItemVO(String name, String description, String processedImage, boolean isNew) {
        this.name = name;
        this.description = description;
        this.processedImage = processedImage;
        this.isNew = isNew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcessedImage() {
        return processedImage;
    }

    public void setProcessedImage(String processedImage) {
        this.processedImage = processedImage;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    @Override
    public String toString() {
        return "ItemVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", processedImage='" + processedImage + '\'' +
                ", isNew=" + isNew +
                '}';
    }
}
