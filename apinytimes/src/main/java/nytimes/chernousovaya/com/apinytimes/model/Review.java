package nytimes.chernousovaya.com.apinytimes.model;

public class Review
{
    private String book_review_link;

    public String getBookReviewLink() { return this.book_review_link; }

    public void setBookReviewLink(String book_review_link) { this.book_review_link = book_review_link; }

    private String first_chapter_link;

    public String getFirstChapterLink() { return this.first_chapter_link; }

    public void setFirstChapterLink(String first_chapter_link) { this.first_chapter_link = first_chapter_link; }

    private String sunday_review_link;

    public String getSundayReviewLink() { return this.sunday_review_link; }

    public void setSundayReviewLink(String sunday_review_link) { this.sunday_review_link = sunday_review_link; }

    private String article_chapter_link;

    public String getArticleChapterLink() { return this.article_chapter_link; }

    public void setArticleChapterLink(String article_chapter_link) { this.article_chapter_link = article_chapter_link; }
}
