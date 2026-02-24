package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Response DTO for post details")
public record PostResponseDTO(
        @Schema(description = "Post unique identifier", example = "1") Long no,

        @Schema(description = "Post title", example = "My First Post") String title,

        @Schema(description = "Post content", example = "Hello, world!") String content,

        @Schema(description = "Creation timestamp") LocalDateTime createdAt,

        @Schema(description = "Last update timestamp") LocalDateTime updatedAt,

        @Schema(description = "View count", example = "42") Integer views,

        @Schema(description = "Post tags", example = "Java, Spring") String tags) {
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
