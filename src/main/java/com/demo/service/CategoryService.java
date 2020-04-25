package com.demo.service;

import com.demo.entity.category.Category;
<<<<<<< HEAD
=======
import com.demo.entity.person.Person;
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
import com.demo.repository.CategoryRepository;
import com.demo.util.CategoryFactory;
import com.demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.demo.util.Constant.*;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    CategoryFactory categoryFactory = new CategoryFactory();

    public String create(String categoryType,
                         String name,
                         String ageRange,
                         float matchTime,
                         String weightRange,
                         int noOfTeamMembers,
                         int noOfMatches){
        Category newCategory  = categoryFactory.createCategory(categoryType, name, ageRange, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if(newCategory == null) return "[ERROR]:Unknown/unsupported category-type [" + categoryType + "]" ;

        String flag = Validator.checkCategory(newCategory);
        if(!flag.equals(SUCCES)) return flag;

        List<Category> allCategory = categoryRepository.findAll();
        for(Category c : allCategory){
            if(c.getName().equals(name)){
                return EXISTS + "category " + newCategory.getName();
            }
        }
        categoryRepository.save(newCategory);
        return SUCCES;
    }

<<<<<<< HEAD
    public String updateCategory( String categoryType,
=======
    public String updatePerson( String categoryType,
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
                                long categoryId,
                                String name,
                                String ageRange,
                                float matchTime,
                                String weightRange,
                                int noOfTeamMembers,
                                int noOfMatches){

        Category  category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null){
            return "Category with id " + categoryId + " not found.";
        }

        Category category1 = categoryRepository.findByName(name);
        if (category1 != null && category1.getCategoryId() != categoryId){
            return "[ERROR]:The category with this name " + name +" already exists.";
        }

        Category newCategory  = categoryFactory.createCategory(categoryType, name, ageRange, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if(newCategory == null) return "[ERROR]:Unknown/unsupported category-type [" + categoryType + "]" ;

        if(newCategory.getClass() != category.getClass()) return "[ERROR]:The category does not have the same type.";

        String flag = Validator.checkCategory(newCategory);
        if(!flag.equals(SUCCES)) return flag;

        newCategory.setCategoryId(category.getCategoryId());
        categoryRepository.save(newCategory);
        return SUCCES;
    }

    public String deleteById(long categoryId){

        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null){
            return "Category with id " + categoryId + " not found.";
        }
        categoryRepository.delete(category);
        return SUCCES;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
<<<<<<< HEAD

    public Category findById(long categoryId){
        return categoryRepository.findById(categoryId).orElse(null);
    }
=======
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
}
