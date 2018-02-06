package nytimes.chernousovaya.com.nytimes.model;

import java.util.Date;

public class Section {
    private String mName;
    private Date mOldestDate;
    private Date mNewestDate;

    public Section(String mName, Date mOldestDate, Date mNewestDate) {
        this.mName = mName;
        this.mOldestDate = mOldestDate;
        this.mNewestDate = mNewestDate;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Date getmOldestDate() {
        return mOldestDate;
    }

    public void setmOldestDate(Date mOldestDate) {
        this.mOldestDate = mOldestDate;
    }

    public Date getmNewestDate() {
        return mNewestDate;
    }

    public void setmNewestDate(Date mNewestDate) {
        this.mNewestDate = mNewestDate;
    }
}
