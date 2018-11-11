package caro.db;

import caro.domain.Blog;

import java.util.List;

public interface BlogRepository {

    Blog get(String blogId);

    List<Blog> getAll();

    void add(Blog blog);

    void update(Blog blog);

    void delete(String blogId);

}
