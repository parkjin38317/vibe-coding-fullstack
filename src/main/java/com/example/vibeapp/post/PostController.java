package com.example.vibeapp.post;

import com.example.vibeapp.common.dto.ApiPagingResponse;
import com.example.vibeapp.common.dto.ErrorResponse;
import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDTO;
import com.example.vibeapp.post.dto.PostUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Post", description = "Board Post Management APIs")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Get paginated posts", description = "Retrieves a list of posts with pagination metadata.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    public ResponseEntity<ApiPagingResponse<PostListDto>> list(
            @Parameter(description = "Page number (1-indexed)", example = "1") @RequestParam(value = "page", defaultValue = "1") int page,
            @Parameter(description = "Number of items per page", example = "5") @RequestParam(value = "size", defaultValue = "5") int size) {

        List<PostListDto> posts = postService.getPostsByPage(page, size);
        int totalPages = postService.getTotalPages(size);
        long totalElements = totalPages * (long) size; // Approximate for now

        ApiPagingResponse<PostListDto> response = new ApiPagingResponse<>(
                posts,
                totalPages,
                page,
                totalElements);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{no}")
    @Operation(summary = "Get post details", description = "Retrieves a single post by its ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved post", content = @Content(schema = @Schema(implementation = PostResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Post not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<PostResponseDTO> detail(
            @Parameter(description = "Post ID", example = "1") @PathVariable("no") Long no) {
        PostResponseDTO post = postService.getPostByNo(no);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    @Operation(summary = "Create post", description = "Creates a new post.")
    @ApiResponse(responseCode = "201", description = "Post created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<Void> add(@Valid @RequestBody PostCreateDto postCreateDto) {
        postService.addPost(postCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{no}")
    @Operation(summary = "Update post", description = "Updates an existing post.")
    @ApiResponse(responseCode = "200", description = "Post updated successfully")
    @ApiResponse(responseCode = "404", description = "Post not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<Void> update(
            @Parameter(description = "Post ID", example = "1") @PathVariable("no") Long no,
            @Valid @RequestBody PostUpdateDto postUpdateDto) {
        postService.updatePost(no, postUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{no}")
    @Operation(summary = "Delete post", description = "Permanently deletes a post.")
    @ApiResponse(responseCode = "204", description = "Post deleted successfully")
    @ApiResponse(responseCode = "404", description = "Post not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<Void> delete(
            @Parameter(description = "Post ID", example = "1") @PathVariable("no") Long no) {
        postService.deletePost(no);
        return ResponseEntity.noContent().build();
    }
}
