package com.example.thawaq.Service;


import com.example.thawaq.Api.ApiException;
import com.example.thawaq.Model.Branch;
import com.example.thawaq.Model.Category;
import com.example.thawaq.Model.Menu;
import com.example.thawaq.Model.StoreAdmin;
import com.example.thawaq.Repository.BranchRepository;
import com.example.thawaq.Repository.CategoryRepository;
import com.example.thawaq.Repository.MenuRepository;
import com.example.thawaq.Repository.StoreAdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BranchRepository branchRepository;
    private final MenuRepository menuRepository;
    private final StoreAdminRepository storeAdminRepository;

    public List<Category> getAllCategories() {
        if (categoryRepository.findAll().isEmpty()) {
            throw new ApiException("Database is empty");
        }
            return categoryRepository.findAll();
        
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public void updateCategory(Category category,Integer id) {
        Category updatedCategory = categoryRepository.findCategoryById(id);
        if (updatedCategory == null) {
            throw new ApiException("Category not found");
        }
        updatedCategory.setName(category.getName());
        updatedCategory.setDescription(category.getDescription());
        categoryRepository.save(updatedCategory);
    }
    public void deleteCategory(Integer id) {
        Category deletedCategory = categoryRepository.findCategoryById(id);
        if (deletedCategory == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(deletedCategory);
    }

    //Jana
    public void applyDiscountToCategoryByName(String categoryName,Integer branchId,Integer storeAdminId ,double discountPercentage) {
        Category category = categoryRepository.findCategoryByName(categoryName);
        Branch branch = branchRepository.findBranchById(branchId);
        StoreAdmin sa = storeAdminRepository.findStoreAdminById(storeAdminId);
        List<Menu> menus = menuRepository.findMenuByCategoryAndBranch(category,branch);
        if (branch == null) {
            throw new ApiException("Branch not found");}
        if (category == null) {
            throw new ApiException("Category with name: " + categoryName + " not found");}
        if(sa == null) {
            throw new ApiException("Store admin not found");}
        if(sa.getStore() != branch.getStore() ){
            throw new ApiException("This branch does not belong to this store admin");}
        if (discountPercentage <= 0 || discountPercentage > 100) {
            throw new ApiException("Invalid discount percentage !");}
        for (Menu menu : menus) {
            double discountedPrice = menu.getPrice() - (menu.getPrice() * discountPercentage / 100);
            menu.setPrice(discountedPrice);}
        categoryRepository.save(category);}

}
