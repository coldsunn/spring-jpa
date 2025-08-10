package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // 이거 없으면 변수명으로 테이블에서 설정되어버림, 칼럼 이름 정해줘야됨
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

//    @JsonIgnore // 회원 정보만 알고 싶으니까 주문 정보는 api에서 제외. but 엔티티에서 화면 종속성 생겨서 별로임
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
