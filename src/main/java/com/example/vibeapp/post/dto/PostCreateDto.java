package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Schema(description = "Request DTO for creating a new post")
public record PostCreateDto(
        @Schema(description = "Post title", example = "My First Post") @NotBlank(message = "제목은 필수입니다.") @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.") String title,

        @Schema(description = "Post content", example = "Hello, world!") String content,

        @Schema(description = "Post tags (comma separated)", example = "Java, Spring, OpenAPI") String tags) {
    public PostCreateDto() {
        this(null, null, null);
    }

    public Post toEntity() {
        Post post = new Post();
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setCreatedAt(LocalDateTime.now());
        post.setViews(0);
        return post;
    }
}
