package caro.domain;

public class Comment {

    private String blogId;
    private String name;
    private int rating;
    private String text;

    public Comment(String name, int rating, String text) {
        this.name = name;
        this.rating = rating;
        this.text = text;
    }

    public Comment(String blogId, String name, int rating, String text) {
        this.blogId = blogId;
        this.name = name;
        this.rating = rating;
        this.text = text;
    }

    public String getBlogId() {
        return blogId;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

}
