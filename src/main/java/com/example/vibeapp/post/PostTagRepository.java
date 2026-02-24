package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PostTagRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PostTag postTag) {
        // 태그 저장: persist()를 사용하여 영속화
        em.persist(postTag);
    }

    public void deleteById(Long id) {
        // ID로 삭제
        PostTag postTag = em.find(PostTag.class, id);
        if (postTag != null) {
            em.remove(postTag);
        }
    }

    public List<PostTag> findByPostNo(Long postNo) {
        // 특정 게시글의 태그 목록 조회
        return em.createQuery("select pt from PostTag pt where pt.postNo = :postNo", PostTag.class)
                .setParameter("postNo", postNo)
                .getResultList();
    }

    public void deleteByPostNo(Long postNo) {
        // 특정 게시글의 모든 태그 삭제
        em.createQuery("delete from PostTag pt where pt.postNo = :postNo")
                .setParameter("postNo", postNo)
                .executeUpdate();
    }
}
