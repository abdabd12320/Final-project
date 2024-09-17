package com.example.thawaq;

import com.example.thawaq.Model.Category;
import com.example.thawaq.Repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void setUp() {

        category = new Category();
        category.setName("Food");
        category.setDescription("All food related items");

        categoryRepository.save(category);
    }

    @Test
    public void testFindCategoryById() {

        Category foundCategory = categoryRepository.findCategoryById(category.getId());
        Assertions.assertThat(foundCategory).isNotNull();
        Assertions.assertThat(foundCategory.getId()).isEqualTo(category.getId());
        Assertions.assertThat(foundCategory.getName()).isEqualTo("Food");
        Assertions.assertThat(foundCategory.getDescription()).isEqualTo("All food related items");
    }

    @Test
    public void testFindCategoryByName() {

        Category foundCategory = categoryRepository.findCategoryByName("Food");
        Assertions.assertThat(foundCategory).isNotNull();
        Assertions.assertThat(foundCategory.getName()).isEqualTo("Food");
        Assertions.assertThat(foundCategory.getDescription()).isEqualTo("All food-related items");
    }
}
