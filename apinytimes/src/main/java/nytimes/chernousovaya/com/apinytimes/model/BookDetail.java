package nytimes.chernousovaya.com.apinytimes.model;

import com.google.gson.annotations.SerializedName;

public class BookDetail {

    private String title;

    private String description;

    private String contributor;

    private String author;

    @SerializedName("contributor_note")
    private String contributorNote;

    private double price;

    @SerializedName("age_group")
    private String ageGroup;

    private String publisher;

    @SerializedName("primary_isbn13")
    private String primaryIsbn13;

    @SerializedName("primary_isbn10")
    private String primaryIsbn10;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrimaryIsbn10() {
        return this.primaryIsbn10;
    }

    public void setPrimaryIsbn10(String primaryIsbn10) {
        this.primaryIsbn10 = primaryIsbn10;
    }

    public String getContributor() {
        return this.contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContributorNote() {
        return this.contributorNote;
    }

    public void setContributorNote(String contributorNote) {
        this.contributorNote = contributorNote;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getAgeGroup() {
        return this.ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrimaryIsbn13() {
        return this.primaryIsbn13;
    }

    public void setPrimaryIsbn13(String primaryIsbn13) {
        this.primaryIsbn13 = primaryIsbn13;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
