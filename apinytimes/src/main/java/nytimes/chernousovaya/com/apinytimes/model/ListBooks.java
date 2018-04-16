package nytimes.chernousovaya.com.apinytimes.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class ListBooks {

    private String status;

    private String copyright;

    @SerializedName("num_results")
    private int numResults;

    @SerializedName("last_modified")
    private Date lastModified;

    private ArrayList<Result> results;

    public ArrayList<Result> getResults() {
        return this.results;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNumResults() {
        return this.numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }


}
