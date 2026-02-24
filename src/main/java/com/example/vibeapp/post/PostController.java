package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDTO;
import com.example.vibeapp.post.dto.PostUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int size = 5;
        List<PostListDto> posts = postService.getPostsByPage(page, size);
        int totalPages = postService.getTotalPages(size);

        // 페이지 블록 (1-5, 6-10 ...)
        int blockLimit = 5;
        int startPage = (((int) Math.ceil((double) page / blockLimit)) - 1) * blockLimit + 1;
        int endPage = Math.min(startPage + blockLimit - 1, totalPages);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "post/posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        PostResponseDTO post = postService.getPostByNo(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/new")
    public String newForm(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto());
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String add(@Valid @ModelAttribute("postCreateDto") PostCreateDto postCreateDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/post_new_form";
        }
        postService.addPost(postCreateDto);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        PostResponseDTO post = postService.getPostByNo(no);
        model.addAttribute("postUpdateDto", new PostUpdateDto(post.title(), post.content(), post.tags()));
        model.addAttribute("no", no);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String update(@PathVariable("no") Long no,
            @Valid @ModelAttribute("postUpdateDto") PostUpdateDto postUpdateDto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("no", no);
            return "post/post_edit_form";
        }
        postService.updatePost(no, postUpdateDto);
        return "redirect:/posts/" + no;
    }

    @GetMapping("/posts/{no}/delete")
    public String delete(@PathVariable("no") Long no) {
        postService.deletePost(no);
        return "redirect:/posts";
    }
}
