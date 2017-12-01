package nytimes.chernousovaya.com.apinytimes.model;

public class NameBooks
{
    private String list_name;

    private String display_name;

    private String list_name_encoded;

    private String oldest_published_date;

    private String newest_published_date;

    private String updated;

    public String getUpdated() { return this.updated; }

    public void setUpdated(String updated) { this.updated = updated; }

    public String getListName() { return this.list_name; }

    public void setListName(String list_name) { this.list_name = list_name; }

    public String getDisplayName() { return this.display_name; }

    public void setDisplayName(String display_name) { this.display_name = display_name; }

    public String getListNameEncoded() { return this.list_name_encoded; }

    public void setListNameEncoded(String list_name_encoded) { this.list_name_encoded = list_name_encoded; }

    public String getOldestPublishedDate() { return this.oldest_published_date; }

    public void setOldestPublishedDate(String oldest_published_date) { this.oldest_published_date = oldest_published_date; }

    public String getNewestPublishedDate() { return this.newest_published_date; }

    public void setNewestPublishedDate(String newest_published_date) { this.newest_published_date = newest_published_date; }

}