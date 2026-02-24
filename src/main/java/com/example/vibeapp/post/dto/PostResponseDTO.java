package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import java.time.LocalDateTime;

public record PostResponseDTO(
        Long no,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer views,
        String tags) {
    public static PostResponseDTO from(Post post) {
        return from(post, null);
    }

    public static PostResponseDTO from(Post post, String tags) {
        return new PostResponseDTO(
                post.getNo(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getViews(),
                tags);
    }
}
