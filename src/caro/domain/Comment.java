package caro.domain;

public class Comment {

    private String blogId;
    private String name;
    private int rating;
    private String text;

    public Comment(String name, int rating, String text) {
        setName(name);
        setRating(rating);
        setText(text);
    }

    public Comment(String blogId, String name, int rating, String text) {
        setBlogId(blogId);
        setName(name);
        setRating(rating);
        setText(text);
    }

    public String getBlogId() {
        return blogId;
    }

    private void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
