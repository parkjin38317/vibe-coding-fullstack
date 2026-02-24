package com.example.vibeapp.post;

import com.example.vibeapp.common.dto.ApiPagingResponse;
import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDTO;
import com.example.vibeapp.post.dto.PostUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<ApiPagingResponse<PostListDto>> list(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {

        List<PostListDto> posts = postService.getPostsByPage(page, size);
        int totalPages = postService.getTotalPages(size);
        long totalElements = totalPages * (long) size; // Approximate for now, as service doesn't provide exact count
                                                       // yet

        ApiPagingResponse<PostListDto> response = new ApiPagingResponse<>(
                posts,
                totalPages,
                page,
                totalElements);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{no}")
    public ResponseEntity<PostResponseDTO> detail(@PathVariable("no") Long no) {
        PostResponseDTO post = postService.getPostByNo(no);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Void> add(@Valid @RequestBody PostCreateDto postCreateDto) {
        postService.addPost(postCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{no}")
    public ResponseEntity<Void> update(@PathVariable("no") Long no,
            @Valid @RequestBody PostUpdateDto postUpdateDto) {
        postService.updatePost(no, postUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> delete(@PathVariable("no") Long no) {
        postService.deletePost(no);
        return ResponseEntity.noContent().build();
    }
}
