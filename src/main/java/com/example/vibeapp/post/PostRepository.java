package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PostRepository {
    List<Post> findAll();

    Optional<Post> findByNo(Long no);

    Post save(Post post);

    void deleteByNo(Long no);

    int count();
}
