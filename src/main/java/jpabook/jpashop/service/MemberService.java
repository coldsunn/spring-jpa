package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 스프링 꺼 쓰자, jpa에서의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 한다, 조회일 때는 읽기 전용이 성능 향상
@RequiredArgsConstructor // final이 있는 필드만 생성자로 만들어줌
public class MemberService {

    private final MemberRepository memberRepository; // 필드 주입, 바꿀 수가 없어서 별로임, setter 주입은 누가 런타임에 바꿀 수도 있음, 얘도 위험 -> 생성자 주입 쓰자

    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증 로직
        memberRepository.save(member);
        return member.getId(); // DB에 접근 안해도 영속성 컨텍스트에 넣을 때도 값이 보장됨
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        //EXCEPTION
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    } // 멤버 수를 세서 0보다 크면 중복이다 이게 좀 더 최적화 된 형태, 또한 멀티 쓰레드 등의 상황에 대비해서 name을 유니크 제약조건으로 잡아줘야됨

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 한 명 조회
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }
}
