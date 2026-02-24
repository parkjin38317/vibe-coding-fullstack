package com.example.vibeapp.post;

import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryPostRepository implements PostRepository {
    private final List<Post> posts = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public MemoryPostRepository() {
        for (int i = 1; i <= 10; i++) {
            save(new Post(
                    null,
                    "게시글 제목 " + i,
                    "게시글 내용 " + i + " 입니다. 메모리 기반 저장소 테스트 진행 중입니다.",
                    LocalDateTime.now().minusDays(10 - i),
                    LocalDateTime.now().minusDays(10 - i),
                    (int) (Math.random() * 100)));
        }
    }

    @Override
    public List<Post> findAll() {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .toList();
    }

    @Override
    public Optional<Post> findByNo(Long no) {
        return posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst();
    }

    @Override
    public Post save(Post post) {
        if (post.getNo() == null) {
            post.setNo(sequence.getAndIncrement());
            posts.add(post);
        } else {
            // 기존 게시글 업데이트 시 위치 교체
            int index = -1;
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getNo().equals(post.getNo())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                posts.set(index, post);
            } else {
                posts.add(post);
            }
        }
        return post;
    }

    @Override
    public void deleteByNo(Long no) {
        posts.removeIf(post -> post.getNo().equals(no));
    }

    @Override
    public int count() {
        return posts.size();
    }
}
