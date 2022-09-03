package com.example.demo.dto;

public class ReportResponse {

    private String id;
    private String comment;
    private String appeared;
    private String sanctioned;
    private String date;

    public ReportResponse() {
    }

    public ReportResponse(String id, String comment, String appeared, String sanctioned, String date) {
        this.id = id;
        this.comment = comment;
        this.appeared = appeared;
        this.sanctioned = sanctioned;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAppeared() {
        return appeared;
    }

    public void setAppeared(String appeared) {
        this.appeared = appeared;
    }

    public String getSanctioned() {
        return sanctioned;
    }

    public void setSanctioned(String sanctioned) {
        this.sanctioned = sanctioned;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
