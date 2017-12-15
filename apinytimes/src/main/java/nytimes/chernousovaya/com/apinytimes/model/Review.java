package nytimes.chernousovaya.com.apinytimes.model;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("book_review_link")
    private String bookReviewLink;

    @SerializedName("first_chapter_link")
    private String firstChapterLink;

    @SerializedName("sunday_review_link")
    private String sundayReviewLink;

    @SerializedName("article_chapter_link")
    private String articleChapterLink;

    public String getArticleChapterLink() {
        return this.articleChapterLink;
    }

    public void setArticleChapterLink(String articleChapterLink) {
        this.articleChapterLink = articleChapterLink;
    }


    public String getBookReviewLink() {
        return this.bookReviewLink;
    }

    public void setBookReviewLink(String bookReviewLink) {
        this.bookReviewLink = bookReviewLink;
    }

    public String getFirstChapterLink() {
        return this.firstChapterLink;
    }

    public void setFirstChapterLink(String firstChapterLink) {
        this.firstChapterLink = firstChapterLink;
    }

    public String getSundayReviewLink() {
        return this.sundayReviewLink;
    }

    public void setSundayReviewLink(String sundayReviewLink) {
        this.sundayReviewLink = sundayReviewLink;
    }
}
