package com.example.sd2020.demo.controller;

import com.example.sd2020.demo.entity.*;
import com.example.sd2020.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/category")
public class CategoryController {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    @PostMapping(value = "/create")
    @ResponseBody
    public List<Category> create(@RequestParam String categoryType,
                                 @RequestParam String name,
                                 @RequestParam String ageRange,
                                 @RequestParam(defaultValue = "1.5") float matchTime,
                                 @RequestParam(defaultValue = "8-35") String weightRange,
                                 @RequestParam(defaultValue = "5") int noOfTeamMembers,
                                 @RequestParam(defaultValue = "3") int noOfMatches){
        CategoryFactory categoryFactory = new CategoryFactory();
        Category newCategory = categoryFactory.getCategory(categoryType);

        newCategory.setName(name);
        newCategory.setAgeRange(ageRange);
        newCategory.setMatchTime(matchTime);
        if(categoryType.equals("Single")){
            ((SingleCategory)newCategory).setWeightRange(weightRange);
        }
        if(categoryType.equals("Team")){
            ((TeamCategory) newCategory).setNoOfTeamMembers(noOfTeamMembers);
            ((TeamCategory) newCategory).setNoOfMatches(noOfMatches);
        }
        categoryRepository.save(newCategory);
        return categoryRepository.findAll();
    }

    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            categoryRepository.deleteAll();
        } catch(Exception ex){
            return "[Error] The records were not deleted. " + ex.getMessage();
        }
        return "The records have been deleted.";
    }

    @GetMapping(value="/findAll")
    public List<Category> findAll(){

        return categoryRepository.findAll();
    }


}
