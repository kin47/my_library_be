package com.example.my_library.dto;

import com.example.my_library.model.Book;
import com.example.my_library.model.Category;

import java.io.Serializable;
import java.util.List;

public class BookDTO implements Serializable {
    private Book book;
    private int total_likes;
    private int total_comments;
    private List<String> categories;

    public BookDTO() {
    }

    public BookDTO(Book book, int total_likes, int total_comments, List<String> categories) {
        this.book = book;
        this.total_likes = total_likes;
        this.total_comments = total_comments;
        this.categories = categories;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getTotal_likes() {
        return total_likes;
    }

    public void setTotal_likes(int total_likes) {
        this.total_likes = total_likes;
    }

    public int getTotal_comments() {
        return total_comments;
    }

    public void setTotal_comments(int total_comments) {
        this.total_comments = total_comments;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
