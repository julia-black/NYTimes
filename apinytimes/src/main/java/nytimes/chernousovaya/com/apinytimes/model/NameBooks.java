package nytimes.chernousovaya.com.apinytimes.model;

import com.google.gson.annotations.SerializedName;

public class NameBooks {
    @SerializedName("list_name")
    private String listName;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("list_name_encoded")
    private String listNameEncoded;

    @SerializedName("oldest_published_date")
    private String oldestPublishedDate;

    @SerializedName("newest_published_date")
    private String newestPublishedDate;

    private String updated;

    public String getUpdated() {
        return this.updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getListName() {
        return this.listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getListNameEncoded() {
        return this.listNameEncoded;
    }

    public void setListNameEncoded(String listNameEncoded) {
        this.listNameEncoded = listNameEncoded;
    }

    public String getOldestPublishedDate() {
        return this.oldestPublishedDate;
    }

    public void setOldestPublishedDate(String oldestPublishedDate) {
        this.oldestPublishedDate = oldestPublishedDate;
    }

    public String getNewestPublishedDate() {
        return this.newestPublishedDate;
    }

    public void setNewestPublishedDate(String newestPublishedDate) {
        this.newestPublishedDate = newestPublishedDate;
    }

    @Override
    public String toString() {
        return "NameBooks{" +
                "listName='" + listName + '\'' +
                '}';
    }
}