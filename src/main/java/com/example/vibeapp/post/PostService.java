package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDTO;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> getPostsByPage(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findByPage(offset, size).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.count();
        return (int) Math.ceil((double) totalPosts / size);
    }

    public void addPost(PostCreateDto dto) {
        Post post = dto.toEntity();
        postRepository.save(post);
    }

    public PostResponseDTO getPostByNo(Long no) {
        // DB에서 조회수 1 증가
        postRepository.increaseViews(no);

        // 증가된 데이터를 포함하여 조회
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));

        return PostResponseDTO.from(post);
    }

    public void updatePost(Long no, PostUpdateDto dto) {
        Post existingPost = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));

        Post updatedPost = dto.toEntity();
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setUpdatedAt(updatedPost.getUpdatedAt());

        postRepository.save(existingPost);
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }
}
