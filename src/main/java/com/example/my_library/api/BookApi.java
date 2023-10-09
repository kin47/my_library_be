package com.example.my_library.api;

import com.example.my_library.dto.BookDTO;
import com.example.my_library.model.Book;
import com.example.my_library.model.Category;
import com.example.my_library.model.ResponseObject;
import com.example.my_library.service.BookService;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookApi {
    public static Logger logger = LoggerFactory.getLogger(BookApi.class);
    private final Date date = new Date();
    @Autowired
    BookService service;

    @GetMapping( "")
    public ResponseEntity<List<BookDTO>> getAll(@RequestParam(required = false) String title, @RequestParam(required = false) String category) {
        List<Book> listBook;
        if(title == null) {
            title = "";
        }
        if(category == null) {
            category = "";
        }
        if(title.isEmpty() && category.isEmpty()) {
            listBook = service.findAll();
        } else {
            listBook = service.findByTitleOrCategoryIgnoreCase(category, title);
        }
        if (listBook == null) {
            ResponseEntity.notFound().build();
        }
        List<BookDTO> bookDTO = new ArrayList<>();
        for (Book book : listBook) {
            int totalLikes = service.getTotalLikesByBookId(book.getId());
            int totalComments = service.getTotalCommentsByBookId(book.getId());
            List<String> categories = service.getCategoriesByBookId(book.getId());
            bookDTO.add(new BookDTO(book, totalLikes, totalComments, categories));
        }
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/get-by-id")
    public BookDTO findBookById(@RequestParam() Long id) {
        Optional<Book> bookOptional = service.findById(id);
        if (bookOptional == null) {
            ResponseEntity.notFound().build();
        }
        Book book = bookOptional.stream().findFirst().orElse(null);
        int totalLikes = service.getTotalLikesByBookId(book.getId());
        int totalComments = service.getTotalCommentsByBookId(book.getId());
        List<String> categories = service.getCategoriesByBookId(book.getId());
        BookDTO bookDTO = new BookDTO(book, totalLikes, totalComments, categories);

        return bookDTO;
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> createNewBook(@Validated @RequestBody Book book) {
        book.setCreate_at(date.getTime());
        book.setUpdate_at(date.getTime());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(201, "Create book successfully", service.save(book)));
    }

    @PutMapping("")
    public ResponseEntity<Book> updateBook(@Validated @RequestBody Book bookForm) {
        long bookId = bookForm.getId();
        Book book = service.getOne(bookId);
        if (book == null) {
            ResponseEntity.notFound().build();
        }
        book.setTitle(bookForm.getTitle());
        book.setAuthor(bookForm.getAuthor());
        book.setContent(bookForm.getContent());
        book.setImage(bookForm.getImage());
        book.setDescription(bookForm.getDescription());
        book.setUpdate_at(date.getTime());

        Book updatedBook = service.save(book);
        return ResponseEntity.ok(updatedBook);
    }
}