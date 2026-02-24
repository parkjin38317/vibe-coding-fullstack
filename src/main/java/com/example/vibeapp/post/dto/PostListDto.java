package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "DTO for post item in list view")
public record PostListDto(
        @Schema(description = "Post unique identifier", example = "1") Long no,

        @Schema(description = "Post title", example = "My First Post") String title,

        @Schema(description = "Creation timestamp") LocalDateTime createdAt,

        @Schema(description = "View count", example = "42") Integer views) {
    public static PostListDto from(Post post) {
        return new PostListDto(
                post.getNo(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getViews());
    }
}
