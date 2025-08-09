package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // 얘네 둘 조합 기억하기, 상속 받은 곳에 @DiscriminatorValue도 필요하면 넣자
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // ==비즈니스 로직==, 서비스가 아닌 엔티티 즉 데이터를 가지고 있는 쪽에 로직이 있는게 객체지향적으로 좋음, setter로 가져오고 그러는거 아님
    // 재고 증가
    public void addStock(int quantity) {
        stockQuantity += quantity;
    }

    // 재고 감소
    public void removeStock(int quantity) {
        int restStock = stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 더 필요합니다.");
        }
        stockQuantity = restStock;
    }
}
