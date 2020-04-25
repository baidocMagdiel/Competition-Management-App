package com.demo.controller;

import com.demo.entity.category.Category;
import com.demo.entity.person.Person;
import com.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.util.Constant.SUCCES;

@RestController
@RequestMapping(value="/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(value="/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam String categoryType,
                                         @RequestParam String name,
                                         @RequestParam String ageRange,
                                         @RequestParam(defaultValue = "1.5") float matchTime,
                                         @RequestParam(defaultValue = "8-35") String weightRange,
                                         @RequestParam(defaultValue = "5") int noOfTeamMembers,
                                         @RequestParam(defaultValue = "3") int noOfMatches){

        String status = categoryService.create(categoryType, name, ageRange, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
<<<<<<< HEAD
        return new ResponseEntity<>("[INFO]:Your category was added.", HttpStatus.OK);
=======
        return new ResponseEntity<>("[INFO]:The category has been added.", HttpStatus.OK);
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
    }

    @PostMapping(value="/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestParam String categoryType,
                                         @RequestParam long categoryId,
                                         @RequestParam String name,
                                         @RequestParam String ageRange,
                                         @RequestParam(defaultValue = "1.5") float matchTime,
                                         @RequestParam(defaultValue = "8-35") String weightRange,
                                         @RequestParam(defaultValue = "5") int noOfTeamMembers,
                                         @RequestParam(defaultValue = "3") int noOfMatches){

<<<<<<< HEAD
        String status = categoryService.updateCategory(categoryType, categoryId, name, ageRange, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your category was updated.", HttpStatus.OK);
=======
        String status = categoryService.updatePerson(categoryType, categoryId, name, ageRange, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:The category has been updated.", HttpStatus.OK);
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam long categoryId) {
        String status = categoryService.deleteById(categoryId);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
<<<<<<< HEAD
        return new ResponseEntity<>("[INFO]:Your category was deleted.", HttpStatus.OK);
=======
        return new ResponseEntity<>("[INFO]:The category has been deleted.", HttpStatus.OK);
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
    }

    @GetMapping(value = "/getall")
    @ResponseBody
    public List<Category> getAll(){
        return categoryService.findAll();
    }



}
