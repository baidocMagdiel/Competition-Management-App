package com.demo.service;

import com.demo.entity.category.Category;
import com.demo.repository.CategoryRepository;
import com.demo.util.CategoryFactory;
import com.demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.util.Constant.EXISTS;
import static com.demo.util.Constant.SUCCES;

/**
 * Clasa de service pentru categorie
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    CategoryFactory categoryFactory = new CategoryFactory();

    /**
     * Metoda pentru crearea unei categorii
     *
     * @param categoryType    tipul categoriei
     * @param name            numele categoriei
     * @param ageRange        intervalul de varsta
     * @param gender          genul(sexul)
     * @param catType         tipul probei
     * @param matchTime       durata unei meci
     * @param weightRange     categoria de greutate
     * @param noOfTeamMembers numarul de membri din echipa
     * @param noOfMatches     numarul de meciuri
     * @return SUCCES sau mesaj de eroare
     */
    public String create(String categoryType,
                         String name,
                         String ageRange,
                         String gender,
                         String catType,
                         float matchTime,
                         String weightRange,
                         int noOfTeamMembers,
                         int noOfMatches) {

        Category newCategory = categoryFactory.createCategory(categoryType, name, ageRange, gender, catType, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if (newCategory == null) return "[ERROR]:Unknown/unsupported category-type [" + categoryType + "]";

        String flag = Validator.checkCategory(newCategory);
        if (!flag.equals(SUCCES)) return flag;

        List<Category> allCategory = categoryRepository.findAll();
        for (Category c : allCategory) {
            if (c.getName().equals(name)) {
                return EXISTS + "category " + newCategory.getName();
            }
        }
        categoryRepository.save(newCategory);
        return SUCCES;
    }

    /**
     * Metoda pentru actualizarea unei categorii
     *
     * @param categoryType    tipul categoriei
     * @param categoryId      id-ul categoriei
     * @param name            numele categoriei
     * @param ageRange        intervalul de varsta
     * @param gender          genul(sexul)
     * @param catType         tipul probei
     * @param matchTime       durata unei meci
     * @param weightRange     categoria de greutate
     * @param noOfTeamMembers numarul de membri din echipa
     * @param noOfMatches     numarul de meciuri
     * @return SUCCES sau mesaj de eroare
     */
    public String updateCategory(String categoryType,
                                 long categoryId,
                                 String name,
                                 String ageRange,
                                 String gender,
                                 String catType,
                                 float matchTime,
                                 String weightRange,
                                 int noOfTeamMembers,
                                 int noOfMatches) {

        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return "Category with id " + categoryId + " not found.";
        }

        Category category1 = categoryRepository.findByName(name);
        if (category1 != null && category1.getCategoryId() != categoryId) {
            return "[ERROR]:The category with this name " + name + " already exists.";
        }

        Category newCategory = categoryFactory.createCategory(categoryType, name, ageRange, gender, catType, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if (newCategory == null) return "[ERROR]:Unknown/unsupported category-type [" + categoryType + "]";

        if (newCategory.getClass() != category.getClass()) return "[ERROR]:The category does not have the same type.";

        String flag = Validator.checkCategory(newCategory);
        if (!flag.equals(SUCCES)) return flag;

        newCategory.setCategoryId(category.getCategoryId());
        categoryRepository.save(newCategory);
        return SUCCES;
    }

    /**
     * Metoda pentru stergerea unei categorii dupa id
     *
     * @param categoryId id-ul categoriei
     * @return SUCCES sau mesaj de eroare
     */
    public String deleteById(long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return "Category with id " + categoryId + " not found.";
        }
        categoryRepository.delete(category);
        return SUCCES;
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
