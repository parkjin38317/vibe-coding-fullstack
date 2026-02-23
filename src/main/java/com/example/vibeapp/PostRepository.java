package com.example.vibeapp;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    Post save(Post post);
}
