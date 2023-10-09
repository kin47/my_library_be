package com.example.my_library.dto;

import java.io.Serializable;

public class HomeAdminDTO implements Serializable {
    private int number_of_users;
    private String most_liked_category;
    private BookDTO recent_addition_book;
    private BookDTO recent_update_book;
    private BookDTO most_liked_book;
    private BookDTO most_comments_book;

    public HomeAdminDTO() {
    }

    public HomeAdminDTO(int number_of_users, String most_liked_category, BookDTO recent_addition_book, BookDTO recent_update_book, BookDTO most_liked_book, BookDTO most_comments_book) {
        this.number_of_users = number_of_users;
        this.most_liked_category = most_liked_category;
        this.recent_addition_book = recent_addition_book;
        this.recent_update_book = recent_update_book;
        this.most_liked_book = most_liked_book;
        this.most_comments_book = most_comments_book;
    }

    public int getNumber_of_users() {
        return number_of_users;
    }

    public void setNumber_of_users(int number_of_users) {
        this.number_of_users = number_of_users;
    }

    public String getMost_liked_category() {
        return most_liked_category;
    }

    public void setMost_liked_category(String most_liked_category) {
        this.most_liked_category = most_liked_category;
    }

    public BookDTO getRecent_addition_book() {
        return recent_addition_book;
    }

    public void setRecent_addition_book(BookDTO recent_addition_book) {
        this.recent_addition_book = recent_addition_book;
    }

    public BookDTO getRecent_update_book() {
        return recent_update_book;
    }

    public void setRecent_update_book(BookDTO recent_update_book) {
        this.recent_update_book = recent_update_book;
    }

    public BookDTO getMost_liked_book() {
        return most_liked_book;
    }

    public void setMost_liked_book(BookDTO most_liked_book) {
        this.most_liked_book = most_liked_book;
    }

    public BookDTO getMost_comments_book() {
        return most_comments_book;
    }

    public void setMost_comments_book(BookDTO most_comments_book) {
        this.most_comments_book = most_comments_book;
    }

    @Override
    public String toString() {
        return "HomeAdminDTO{" +
                "number_of_users=" + number_of_users +
                ", most_liked_category='" + most_liked_category + '\'' +
                ", recent_addition_book=" + recent_addition_book +
                ", recent_update_book=" + recent_update_book +
                ", most_liked_book=" + most_liked_book +
                ", most_comments_book=" + most_comments_book +
                '}';
    }
}

