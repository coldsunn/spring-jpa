package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈 자동 등록
@RequiredArgsConstructor
public class MemberRepository {

    /*
    @PersistenceContext
    private EntityManager em; // 스프링이 엔티티 매니저를 자동으로 만들어서 주입해줌
    */

    private final EntityManager em; // 생성자 주입을 이용하는 스프링 데이터 JPA 방식

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
