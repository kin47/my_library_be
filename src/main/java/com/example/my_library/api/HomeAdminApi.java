package com.example.my_library.api;

import com.example.my_library.dto.BookDTO;
import com.example.my_library.dto.HomeAdminDTO;
import com.example.my_library.model.Book;
import com.example.my_library.service.BookService;
import com.example.my_library.service.HomeAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/home/admin")
public class HomeAdminApi {
    public static Logger logger = LoggerFactory.getLogger(HomeAdminApi.class);

    @Autowired
    HomeAdminService service;

    @Autowired
    BookService bookService;

    @GetMapping(value = "")
    public HomeAdminDTO getHomeAdminInfo() {
        HomeAdminDTO homeAdminDTO = new HomeAdminDTO();
        homeAdminDTO.setNumber_of_users(service.getNumberOfUsers());
        homeAdminDTO.setMost_liked_category(service.getMostLikedCategory());
        homeAdminDTO.setRecent_addition_book(convertOptionalBookToBookDTO(service.getRecentAdditionBook()));
        homeAdminDTO.setRecent_update_book(convertOptionalBookToBookDTO(service.getRecentUpdateBook()));
        homeAdminDTO.setMost_liked_book(convertOptionalBookToBookDTO(service.getMostLikedBook()));
        homeAdminDTO.setMost_comments_book(convertOptionalBookToBookDTO(service.getMostCommentsBook()));
        return homeAdminDTO;
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
