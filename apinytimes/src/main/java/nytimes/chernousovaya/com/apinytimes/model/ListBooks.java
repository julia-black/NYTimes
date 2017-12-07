package nytimes.chernousovaya.com.apinytimes.model;

import java.util.ArrayList;
import java.util.Date;

public class ListBooks {

    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

    private String copyright;

    public String getCopyright() { return this.copyright; }

    public void setCopyright(String copyright) { this.copyright = copyright; }

    private int num_results;

    public int getNumResults() { return this.num_results; }

    public void setNumResults(int num_results) { this.num_results = num_results; }

    private Date last_modified;

    public Date getLastModified() { return this.last_modified; }

    public void setLastModified(Date last_modified) { this.last_modified = last_modified; }

    private ArrayList<Result> results;

    public ArrayList<Result> getResults() { return this.results; }

    public void setResults(ArrayList<Result> results) { this.results = results; }
}
