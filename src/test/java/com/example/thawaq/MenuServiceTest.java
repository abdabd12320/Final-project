package com.example.thawaq;

import com.example.thawaq.Model.Branch;
import com.example.thawaq.Model.Category;
import com.example.thawaq.Model.Menu;
import com.example.thawaq.Model.User;
import com.example.thawaq.Repository.CategoryRepository;
import com.example.thawaq.Repository.MenuRepository;
import com.example.thawaq.Service.MenuService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @InjectMocks
    MenuService menuService;
    @Mock
    MenuRepository MenuRepository;
    @Mock
    CategoryRepository CategoryRepository;
    Category category;
    Branch branch;
    Menu menu1, menu2;
    List<Menu> menus;

    @BeforeEach
    void setUp() {
        category = new Category(null, "Desserts", "A variety of sweet dishes to choose from", null);
        menu1 = new Menu(null, "food", 50,300, "Every food", "https://cdn.salla.sa/bKWBK/96309dba-81e2-4949-b825-ed0b057484bd-1000x551.1811023622-c305zOVXGkaefIv9GzheLwBlXfRvfrKxu688qcnX.jpg", null, null, null);
        menu2 = new Menu(null, "food", 40,300, "Every food", "https://cdn.salla.sa/bKWBK/96309dba-81e2-4949-b825-ed0b057484bd-1000x551.1811023622-c305zOVXGkaefIv9GzheLwBlXfRvfrKxu688qcnX.jpg", null, null, null);
        menus.add(menu1);
        menus.add(menu2);
    }

    @Test
    public void getAllMenus() {
        when(MenuRepository.findAll()).thenReturn(menus);
        List<Menu>Menulist = menuService.getAllMenus();
        Assertions.assertEquals(9, Menulist.size());
        verify(MenuRepository, times(1)).findAll();
    }

    @Test
    public void AddMenus() {
        when(CategoryRepository.findCategoryById(category.getId())).thenReturn(category);
        menuService.addMenu(menu2, category.getId(), branch.getId());
        verify(CategoryRepository, times(1)).findCategoryById(category.getId());
        verify(MenuRepository, times(1)).save(menu2);
    }

    @Test
    public void updateMenuTest(){
        when(CategoryRepository.findCategoryById(category.getId())).thenReturn(category);
        when(MenuRepository.findMenuById(menu1.getId())).thenReturn(menu1);
        when(MenuRepository.findMenuById(menu2.getId())).thenReturn(menu2);
        menuService.updateMenu(menu1, category.getId(), branch.getId());
        verify(CategoryRepository, times(1)).findCategoryById(category.getId());
        verify(MenuRepository,times(1)).findMenuById(menu1.getId());
        verify(MenuRepository, times(1)).save(menu2);


    }
    @Test
    public void DeleteMenuTest(){
        when(MenuRepository.findMenuById(menu1.getId())).thenReturn(menu1);
        when(CategoryRepository.findCategoryById(category.getId())).thenReturn(category);
        menuService.deleteMenu(menu1.getId());
        verify(CategoryRepository, times(1)).findCategoryById(category.getId());
        verify(MenuRepository, times(1)).findMenuById(menu1.getId());
        verify(MenuRepository,times(1)).delete(menu1);



    }

}
