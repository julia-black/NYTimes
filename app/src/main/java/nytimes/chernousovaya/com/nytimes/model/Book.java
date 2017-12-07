package nytimes.chernousovaya.com.nytimes.model;


public class Book {

    private String mTitle;

    private String mDescription;

    private String mContributor;

    private String mAuthor;


    private String mPublisher;

    private String mUrl;

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
                '}';
    }
}
