package com.demo.service;

import com.demo.entity.category.Category;
import com.demo.entity.category.CategoryDto;
import com.demo.repository.CategoryRepository;
import com.demo.util.CategoryFactory;
import com.demo.util.Validator;
import com.demo.util.exception.AppRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.util.Constant.*;

/**
 * Clasa de service pentru categorie
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    CategoryFactory categoryFactory = new CategoryFactory();


    public Category create(CategoryDto categoryDto) {

        Category newCategory = categoryFactory.createCategory(categoryDto);

        Validator.checkCategory(newCategory);

        List<Category> allCategory = categoryRepository.findAll();
        for (Category c : allCategory) {
            if (c.getName().equals(categoryDto.getName())) {
                throw new AppRequestException(CATEGORY_EXISTS, HttpStatus.BAD_REQUEST);
            }
        }
        categoryRepository.save(newCategory);
        return newCategory;
    }


    public Category updateCategory(CategoryDto categoryDto) {

        Category category = categoryRepository.findById(categoryDto.getId()).orElse(null);
        if (category == null) {
            throw new AppRequestException("Category with id " + categoryDto.getId() + " not found.", HttpStatus.BAD_REQUEST);
        }

        Category category1 = categoryRepository.findByName(categoryDto.getName());
        if (category1 != null && category1.getCategoryId() != categoryDto.getId()) {
            throw new AppRequestException("Category with this name " + categoryDto.getName() + " already exists.", HttpStatus.BAD_REQUEST);
        }

        Category newCategory = categoryFactory.createCategory(categoryDto);

        if (newCategory.getClass() != category.getClass()) throw new AppRequestException("Category does not have the same type.", HttpStatus.BAD_REQUEST);

        Validator.checkCategory(newCategory);

        newCategory.setCategoryId(category.getCategoryId());
        categoryRepository.save(newCategory);
        return newCategory;
    }

    /**
     * Metoda pentru stergerea unei categorii dupa id
     *
     * @param categoryId id-ul categoriei
     * @return SUCCES sau mesaj de eroare
     */
    public Category deleteById(long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new AppRequestException("Category with id " + categoryId + " not found.", HttpStatus.BAD_REQUEST);
        }
        categoryRepository.delete(category);
        return category;
    }

    /**
     * Metoda pentru returnarea tuturor categoriilor din baza de date
     *
     * @return lista categoriilor
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Metoda pentru cautarea in baza de date a unei categorii dupa id
     *
     * @param categoryId id-ul categoriei
     * @return categoria sau null
     */
    public Category findById(long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

}
