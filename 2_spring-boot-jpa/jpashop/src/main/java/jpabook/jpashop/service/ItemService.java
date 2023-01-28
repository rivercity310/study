package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
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

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /* 변경 감지 방식 : merge 사용하지 말것 -> 모든 필드를 업데이트 하므로 null 위험성이 있다 */
    @Transactional
    public void updateItem(Long itemId, UpdateItemDto itemDto) {
        Book findItem = (Book) itemRepository.findOne(itemId);

        // setter보다 change와 같은 의미있는 메서드를 따로 만드는 방식이 권장된다.
        findItem.setPrice(itemDto.getPrice());
        findItem.setName(itemDto.getName());
        findItem.setStockQuantity(itemDto.getStockQuantity());
        findItem.setAuthor(itemDto.getAuthor());
        findItem.setIsbn(itemDto.getIsbn());

        // findItem: 트랜잭션 내에서 찾았으므로 영속 상태,
        //      - 변경 감지 -> update 쿼리 자동으로 날려줌
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}