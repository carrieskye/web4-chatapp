package caro.domain;

import java.util.ArrayList;

public class Blog {

    private String blogId;
    private String text;
    private ArrayList<Comment> comments;

    public Blog(String blogId, String text, ArrayList<Comment> comments) {
        setBlogId(blogId);
        setText(text);
        setComments(comments);
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
