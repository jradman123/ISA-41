package com.example.demo.mapper;

import com.example.demo.dto.ImageRequest;
import com.example.demo.dto.ImagesResponse;
import com.example.demo.model.Image;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ImageMapper {

    public Image mapImageRequestToImage(ImageRequest imageRequest){
        Image image = new Image();
        image.setUrl(imageRequest.getUrl());
        return image;
    }

    public ImagesResponse mapImagesToImagesResponse(Set<Image> images){
        List<ImageRequest> imageResponse = new ArrayList<>();
        for (Image image: images) {
            imageResponse.add(new ImageRequest(image.getUrl()));
        }
        return new ImagesResponse(imageResponse);

    }
}
