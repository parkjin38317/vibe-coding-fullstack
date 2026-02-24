package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PostRepository {
    List<Post> findAll();

    List<Post> findByPage(@Param("offset") int offset, @Param("limit") int limit);

    Optional<Post> findByNo(Long no);

    void save(Post post);

    void deleteByNo(Long no);

    void increaseViews(Long no);

    int count();
}
