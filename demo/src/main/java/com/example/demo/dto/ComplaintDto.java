package com.example.demo.dto;

public class ComplaintDto {

    private String id;
    private String ownersName;
    private String comment;

    public ComplaintDto() {
    }

    public ComplaintDto(String id,String ownersName, String comment) {
        this.id = id;
        this.ownersName = ownersName;
        this.comment = comment;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
