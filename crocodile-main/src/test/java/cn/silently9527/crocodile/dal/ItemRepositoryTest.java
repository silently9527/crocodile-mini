package cn.silently9527.crocodile.dal;

import cn.silently9527.crocodile.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ItemRepositoryTest extends BaseTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testFindByName() {
        System.out.println( itemRepository.findAll());
    }

}