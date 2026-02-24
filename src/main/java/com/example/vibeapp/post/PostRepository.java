package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Post> findAll() {
        // JPQL을 사용하여 모든 게시글 조회
        return em.createQuery("select p from Post p order by p.no desc", Post.class)
                .getResultList();
    }

    public List<Post> findByPage(int offset, int limit) {
        // JPQL 페이징 처리
        return em.createQuery("select p from Post p order by p.no desc", Post.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public Optional<Post> findByNo(Long no) {
        // EntityManager.find()를 사용하여 단건 조회
        return Optional.ofNullable(em.find(Post.class, no));
    }

    public void save(Post post) {
        if (post.getNo() == null) {
            // 신규 등록: 영속성 컨텍스트에 저장
            em.persist(post);
        } else {
            // 수정: merge()를 사용하여 준영속 상태의 엔티티를 영속 상태로 병합
            // 또는 Service 단계의 트랜잭션 내에서 변경 감지(Dirty Checking)가 작동함
            em.merge(post);
        }
    }

    public void deleteByNo(Long no) {
        // 삭제: 조회 후 remove() 호출
        Post post = em.find(Post.class, no);
        if (post != null) {
            em.remove(post);
        }
    }

    public void increaseViews(Long no) {
        // 조회수 증가: 직접 쿼리 실행 또는 엔티티 조회 후 수정
        em.createQuery("update Post p set p.views = p.views + 1 where p.no = :no")
                .setParameter("no", no)
                .executeUpdate();
    }

    public int count() {
        // 전체 개수 조회
        return em.createQuery("select count(p) from Post p", Long.class)
                .getSingleResult().intValue();
    }
}
