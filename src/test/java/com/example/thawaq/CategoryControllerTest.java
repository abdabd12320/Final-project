package com.example.thawaq;

import com.example.thawaq.Api.ApiResponse;
import com.example.thawaq.Controller.CategoryController;
import com.example.thawaq.Model.Category;
import com.example.thawaq.Service.CategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CategoryController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CategoryControllerTest {

    @MockBean
    CategoryService categoryService;

    @Autowired
    MockMvc mockMvc;

    Category category1,category2;

    ApiResponse apiResponse;

    List<Category> categories;

    @BeforeEach
    void setUp()
    {
        category1 = new Category(null,"name","description",null);

        apiResponse = new ApiResponse("ApiResponse");

        category2 = new Category(null,"name","description",null);

        categories = new ArrayList<>();

        categories.add(category1);
        categories.add(category2);
    }

    @Test
    public void getAllRatings() throws Exception {
        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/api/v1/category/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("name"));
    }
}
