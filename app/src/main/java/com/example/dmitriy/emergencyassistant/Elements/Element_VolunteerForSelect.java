package com.example.dmitriy.emergencyassistant.Elements;

public class Element_VolunteerForSelect {

    private String id;
    private String initials;
    private String review;

    public Element_VolunteerForSelect(String id, String initials, String review){
        this.id = id;
        this.initials = initials;
        this.review = review;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInitials() {
        return this.initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
