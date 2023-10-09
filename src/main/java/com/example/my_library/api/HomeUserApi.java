package com.example.my_library.api;

import com.example.my_library.dto.BookDTO;
import com.example.my_library.dto.HomeUserDTO;
import com.example.my_library.model.Book;
import com.example.my_library.service.BookService;
import com.example.my_library.service.HomeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/home/user")
public class HomeUserApi {
    public static Logger logger = LoggerFactory.getLogger(HomeUserApi.class);

    @Autowired
    HomeUserService service;

    @Autowired
    BookService bookService;

    @GetMapping(value = "")
    public HomeUserDTO getHomeUserInfo() {
        HomeUserDTO homeUserDTO = new HomeUserDTO();
        homeUserDTO.setContinue_reading(convertOptionalBookToBookDTO(service.getRecentUpdateBook()));
        homeUserDTO.setRecommendation(convertOptionalBookToBookDTO(service.getRecommendationBook()));
        homeUserDTO.setRecent_addition_book(convertOptionalBookToBookDTO(service.getRecentAdditionBook()));
        homeUserDTO.setRecent_update_book(convertOptionalBookToBookDTO(service.getRecentUpdateBook()));
        return homeUserDTO;
    }

    public BookDTO convertOptionalBookToBookDTO(Optional<Book> bookOptional) {
        Book book = bookOptional.stream().findFirst().orElse(null);
        int totalLikes = bookService.getTotalLikesByBookId(book.getId());
        int totalComments = bookService.getTotalCommentsByBookId(book.getId());
        List<String> categories = bookService.getCategoriesByBookId(book.getId());
        BookDTO bookDTO = new BookDTO(book, totalLikes, totalComments, categories);
        return bookDTO;
    }
}
