package nytimes.chernousovaya.com.nytimes.model;

import java.util.Date;

public class Book {

    private String mTitle;

    private String mDescription;

    private String mContributor;

    private String mAuthor;

    private String mPublisher;

    private String mUrl;

    private int mRank;

    private int mRankLastWeek;

    private Date mBestsellerDate;

    public Book() {

    }

    public Book(String mTitle, String mDescription, String mContributor, String mAuthor, String mPublisher, String mUrl, int mRank, int mRankLastWeek, Date mBestsellerDate) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mContributor = mContributor;
        this.mAuthor = mAuthor;
        this.mPublisher = mPublisher;
        this.mUrl = mUrl;
        this.mRank = mRank;
        this.mRankLastWeek = mRankLastWeek;
        this.mBestsellerDate = mBestsellerDate;
    }

    public int getmRank() {
        return mRank;
    }

    public void setmRank(int mRank) {
        this.mRank = mRank;
    }

    public int getmRankLastWeek() {
        return mRankLastWeek;
    }

    public void setmRankLastWeek(int mRankLastWeek) {
        this.mRankLastWeek = mRankLastWeek;
    }

    public Date getmBestsellerDate() {
        return mBestsellerDate;
    }

    public void setmBestsellerDate(Date mBestsellerDate) {
        this.mBestsellerDate = mBestsellerDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmContributor() {
        return mContributor;
    }

    public void setmContributor(String mContributor) {
        this.mContributor = mContributor;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmPublisher() {
        return mPublisher;
    }

    public void setmPublisher(String mPublisher) {
        this.mPublisher = mPublisher;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }


    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mContributor='" + mContributor + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mPublisher='" + mPublisher + '\'' +
                ", mUrl='" + mUrl + '\'' +
                ", mRank='" + mRank + '\'' +
                ", mRankLastWeek='" + mRankLastWeek + '\'' +
                '}';
    }
}
