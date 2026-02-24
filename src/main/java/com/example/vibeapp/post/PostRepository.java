package com.example.vibeapp.post;

import java.util.List;

import java.util.Optional;

public interface PostRepository {
    List<Post> findAll();

    Optional<Post> findByNo(Long no);

    Post save(Post post);

    void deleteByNo(Long no);

    int count();
}
