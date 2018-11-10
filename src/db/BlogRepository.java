package db;

import domain.Blog;

import java.util.List;

public interface BlogRepository {

    void add(Blog blog);

    void delete(String blogId);

    Blog get(String blogId);

    List<Blog> getAll();

    void update(Blog blog);

}