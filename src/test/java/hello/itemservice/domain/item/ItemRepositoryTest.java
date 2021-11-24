package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("상품 등록")
    void save() {
        // given
        Item item = new Item("Book", 20000, 13);
        // when
        Item saveItem = itemRepository.save(item);
        // then
        Item findItem = itemRepository.findById(saveItem.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    @DisplayName("상품 목록")
    void findAll() {
        // given
        Item item1 = new Item("Item1", 20000, 100);
        Item item2 = new Item("Item2", 3000000, 1);
        // when
        Item saveItemA = itemRepository.save(item1);
        Item saveItemB = itemRepository.save(item2);
        // then
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1, item2);
    }

    @Test
    @DisplayName("상품 수정")
    void updateItem() {
        // given
        Item itemA = new Item("book", 20000, 100);
        Item saveItemA = itemRepository.save(itemA);
        Long itemId = saveItemA.getId();
        // when
        Item updateParam = new Item("Book", 19999, 0);
        itemRepository.update(itemId, updateParam);
        // then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}