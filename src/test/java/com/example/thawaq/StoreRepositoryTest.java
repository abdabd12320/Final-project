package com.example.thawaq;

import com.example.thawaq.Model.Store;
import com.example.thawaq.Repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StoreRepositoryTest {
    @Autowired
    StoreRepository storeRepository;

    Store store;

    Store store1 , store2 , store3 ;

    List<Store> stores;

    @BeforeEach
    void setUp() {
        store1 = new Store(null,"Java Cafe","CAFE","0534384337","12345678","abshdbjddjd.img",false,null,null,null,null);
        store2 = new Store(null,"Dunken Cafe","CAFE","0534384387","12342678","abfjfhdbjddjd.img",false,null,null,null,null);
        store3 = new Store(null,"Cacti Cafe","RESTAURANT","0539384337","12335678","abshdbpeoddjd.img",false,null,null,null,null);
    }

    @Test
    public void findStoreById(){
        storeRepository.save(store1);
        store = storeRepository.findStoreById(store1.getId());
        Assertions.assertThat(store.getId()).isEqualTo(store1.getId());
    }

    @Test
    public void findStoreByName(){
        storeRepository.save(store2);
        stores = storeRepository.findStoreByName("Dunken Cafe");
        Assertions.assertThat(stores.get(0).getId()).isEqualTo(store2.getId());
    }

    @Test
    public void findStoreByTypeOfActivity(){
        storeRepository.save(store3);
        stores = storeRepository.findStoreByTypeOfActivity("RESTAURANT");
        Assertions.assertThat(stores.get(0).getId()).isEqualTo(store3.getId());
    }
}
