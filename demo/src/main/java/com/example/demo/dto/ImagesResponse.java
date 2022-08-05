package com.example.demo.dto;

import java.util.List;

public class ImagesResponse {

    private List<ImageRequest> images;

    public ImagesResponse() {
    }

    public ImagesResponse(List<ImageRequest> images) {
        this.images = images;
    }

    public List<ImageRequest> getImages() {
        return images;
    }

    public void setImages(List<ImageRequest> images) {
        this.images = images;
    }
}
