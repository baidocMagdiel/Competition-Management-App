package com.demo.controller;

import com.demo.entity.Category;
import com.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/category")
public class CategoryController {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController() { this.categoryRepository = new CategoryRepository(); }

    @PostMapping(value = "/create")
    @ResponseBody
    public List<Category> create(@RequestParam String categoryType,
                                 @RequestParam String name,
                                 @RequestParam String ageRange,
                                 @RequestParam(defaultValue = "1.5") float matchTime,
                                 @RequestParam(defaultValue = "8-35") String weightRange,
                                 @RequestParam(defaultValue = "5") int noOfTeamMembers,
                                 @RequestParam(defaultValue = "3") int noOfMatches){

        Category newCategory = new Category();

        newCategory.setDtype(categoryType);
        newCategory.setName(name);
        newCategory.setAgeRange(ageRange);
        newCategory.setMatchTime(matchTime);
        if(categoryType.equals("Single")){
            newCategory.setWeightRange(weightRange);
        }
        if(categoryType.equals("Team")){
            newCategory.setNoOfTeamMembers(noOfTeamMembers);
            newCategory.setNoOfMatches(noOfMatches);
        }
        categoryRepository.insert(newCategory);
        return categoryRepository.findAll("*");
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

        return categoryRepository.findAll("*");
    }
}
