package com.example.demo.dto;


import com.example.demo.model.Image;
import com.example.demo.model.Utility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    public String id;
    private String url;

    public ImageDto(Image image) {
        this.id=Long.toString(image.getId());
        this.url=image.getUrl();
    }

}
