package com.example.my_library.api;

import com.example.my_library.model.Category;
import com.example.my_library.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryApi {
    public static Logger logger = LoggerFactory.getLogger(CategoryApi.class);
    private final Date date = new Date();
    @Autowired
    CategoryService service;
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> listAllCategory() {
        List<Category> listCategory = service.findAll();
        if(listCategory.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(listCategory, HttpStatus.OK);
    }
}
