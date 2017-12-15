package nytimes.chernousovaya.com.apinytimes.model;

public class Isbn {
    private String isbn10;

    private String isbn13;

    public String getIsbn10() {
        return this.isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return this.isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
}
