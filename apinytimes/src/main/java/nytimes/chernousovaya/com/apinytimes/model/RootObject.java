package nytimes.chernousovaya.com.apinytimes.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootObject {

    private String status;

    private String copyright;

    private int num_results;

    @SerializedName("results")
    private ArrayList<NameBooks> namesBooks;

    public ArrayList<NameBooks> getNamesBooks() {
        return this.namesBooks;
    }

    public void setNamesBooks(ArrayList<NameBooks> namesBooks) {
        this.namesBooks = namesBooks;
    }
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    public int getNumResults() {
        return this.num_results;
    }

    public void setNumResults(int num_results) {
        this.num_results = num_results;
    }

}