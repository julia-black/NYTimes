package nytimes.chernousovaya.com.apinytimes.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {
    @SerializedName("list_name")
    private String listName;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("bestsellers_date")
    private String bestsellersDate;

    @SerializedName("published_date")
    private String publishedDate;

    private int rank;

    @SerializedName("rank_last_week")
    private int rankLastWeek;

    @SerializedName("week_on_list")
    private int weeksOnList;

    private int asterisk;

    private int dagger;

    @SerializedName("amazon_product_url")
    private String amazonProductUrl;

    private ArrayList<Isbn> isbns;

    @SerializedName("book_details")
    private ArrayList<BookDetail> bookDetails;

    private ArrayList<Review> reviews;

    public String getlistName() {
        return listName;
    }

    public void setlistName(String listName) {
        this.listName = listName;
    }

    public String getdisplayName() {
        return displayName;
    }

    public void setdisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getbestsellersDate() {
        return bestsellersDate;
    }

    public void setbestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }

    public String getpublishedDate() {
        return publishedDate;
    }

    public void setpublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getrankLastWeek() {
        return rankLastWeek;
    }

    public void setrankLastWeek(int rankLastWeek) {
        this.rankLastWeek = rankLastWeek;
    }

    public int getweeksOnList() {
        return weeksOnList;
    }

    public void setweeksOnList(int weeksOnList) {
        this.weeksOnList = weeksOnList;
    }

    public int getAsterisk() {
        return asterisk;
    }

    public void setAsterisk(int asterisk) {
        this.asterisk = asterisk;
    }

    public int getDagger() {
        return dagger;
    }

    public void setDagger(int dagger) {
        this.dagger = dagger;
    }

    public String getAmazonProductUrl() {
        return amazonProductUrl;
    }

    public void setAmazonProductUrl(String amazonProductUrl) {
        this.amazonProductUrl = amazonProductUrl;
    }

    public ArrayList<Isbn> getIsbns() {
        return isbns;
    }

    public void setIsbns(ArrayList<Isbn> isbns) {
        this.isbns = isbns;
    }

    public ArrayList<BookDetail> getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(ArrayList<BookDetail> bookDetails) {
        this.bookDetails = bookDetails;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}