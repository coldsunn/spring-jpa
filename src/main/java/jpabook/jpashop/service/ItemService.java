package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 상품 저장
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 상품 조회
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    // 상품 하나만 조회
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
    // 우리가 작성한 코드처럼 리포지토리 기능을 그냥 가져다 똑같이 쓰는 경우에는 컨트롤러에서 리포지토리에 바로 접근해도 괜춘
    // 테스트 생략
}
