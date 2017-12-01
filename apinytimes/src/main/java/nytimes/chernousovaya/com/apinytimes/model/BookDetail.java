package nytimes.chernousovaya.com.apinytimes.model;

public class BookDetail
{
    private String title;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    private String description;

    private String contributor;

    private String author;

    private String contributor_note;

    private int price;

    private String age_group;

    private String publisher;

    private String primary_isbn13;

    private String primary_isbn10;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    public String getPrimaryIsbn10() { return this.primary_isbn10; }

    public void setPrimaryIsbn10(String primary_isbn10) { this.primary_isbn10 = primary_isbn10; }

    public String getContributor() { return this.contributor; }

    public void setContributor(String contributor) { this.contributor = contributor; }

    public String getAuthor() { return this.author; }

    public void setAuthor(String author) { this.author = author; }

    public String getContributorNote() { return this.contributor_note; }

    public void setContributorNote(String contributor_note) { this.contributor_note = contributor_note; }

    public int getPrice() { return this.price; }

    public void setPrice(int price) { this.price = price; }


    public String getAgeGroup() { return this.age_group; }

    public void setAgeGroup(String age_group) { this.age_group = age_group; }

    public String getPublisher() { return this.publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getPrimaryIsbn13() { return this.primary_isbn13; }

    public void setPrimaryIsbn13(String primary_isbn13) { this.primary_isbn13 = primary_isbn13; }

}
