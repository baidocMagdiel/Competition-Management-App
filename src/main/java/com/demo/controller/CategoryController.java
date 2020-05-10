package com.demo.controller;

import com.demo.entity.category.Category;
import com.demo.entity.category.CategoryDto;
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
     * Metoda pentru adaugarea in DB a unei noi categorii
     *
     * @param categoryDto categoria ce trebuie adaugata
     * @return categoria adaugata
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public Category create(@RequestBody CategoryDto categoryDto) {

        return categoryService.create(categoryDto);
    }

    /**
     * Metoda pentru actualizarea unei categorii
     *
     * @param categoryDto actualizarea categoriei
     * @return categoria actualizata
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Category update(@RequestBody CategoryDto categoryDto) {

        return categoryService.updateCategory(categoryDto);
    }

    /**
     * Metoda pentru stergerea unei categorii dupa id
     *
     * @param categoryId id-ul categoriei
     * @return mesaj corespunzator
     */
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public Category delete(@RequestParam long categoryId) {

        return categoryService.deleteById(categoryId);
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
