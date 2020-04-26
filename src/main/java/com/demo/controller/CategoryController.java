package com.demo.controller;

import com.demo.entity.category.Category;
import com.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.util.Constant.SUCCES;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

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
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam String categoryType,
                                         @RequestParam String name,
                                         @RequestParam String ageRange,
                                         @RequestParam String gender,
                                         @RequestParam String catType,
                                         @RequestParam(defaultValue = "1.5") float matchTime,
                                         @RequestParam(defaultValue = "8-35") String weightRange,
                                         @RequestParam(defaultValue = "5") int noOfTeamMembers,
                                         @RequestParam(defaultValue = "3") int noOfMatches) {

        String status = categoryService.create(categoryType, name, ageRange, gender, catType, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("[INFO]:Your category was added.", HttpStatus.OK);
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
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestParam String categoryType,
                                         @RequestParam long categoryId,
                                         @RequestParam String name,
                                         @RequestParam String ageRange,
                                         @RequestParam String gender,
                                         @RequestParam String catType,
                                         @RequestParam(defaultValue = "1.5") float matchTime,
                                         @RequestParam(defaultValue = "8-35") String weightRange,
                                         @RequestParam(defaultValue = "5") int noOfTeamMembers,
                                         @RequestParam(defaultValue = "3") int noOfMatches) {

        String status = categoryService.updateCategory(categoryType, categoryId, name, ageRange, gender, catType, matchTime, weightRange, noOfTeamMembers, noOfMatches);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your category was updated.", HttpStatus.OK);
    }

    /**
     * Metoda pentru stergerea unei categorii dupa id
     *
     * @param categoryId id-ul categoriei
     * @return mesaj corespunzator
     */
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam long categoryId) {
        String status = categoryService.deleteById(categoryId);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your category was deleted.", HttpStatus.OK);
    }

    /**
     * Metoda pentru returnarea tuturor categoriilor din baza de date
     *
     * @return lista categoriilor
     */
    @GetMapping(value = "/getall")
    @ResponseBody
    public List<Category> getAll() {
        return categoryService.findAll();
    }


}
