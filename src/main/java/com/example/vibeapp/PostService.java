package com.example.vibeapp;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostByNo(Long no) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        
        // 상세 조회 시 조회수 증가
        post.setViews(post.getViews() + 1);
        return post;
    }
}
