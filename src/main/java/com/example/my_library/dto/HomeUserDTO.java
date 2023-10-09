package com.example.my_library.dto;

import java.io.Serializable;

public class HomeUserDTO implements Serializable {
    private BookDTO continue_reading;
    private BookDTO recommendation;
    private BookDTO recent_addition_book;
    private BookDTO recent_update_book;

    public HomeUserDTO() {
    }

    public HomeUserDTO(BookDTO continue_reading, BookDTO recommendation, BookDTO recent_addition_book, BookDTO recent_update_book) {
        this.continue_reading = continue_reading;
        this.recommendation = recommendation;
        this.recent_addition_book = recent_addition_book;
        this.recent_update_book = recent_update_book;
    }

    public BookDTO getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(BookDTO recommendation) {
        this.recommendation = recommendation;
    }

    public BookDTO getContinue_reading() {
        return continue_reading;
    }

    public void setContinue_reading(BookDTO continue_reading) {
        this.continue_reading = continue_reading;
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
}
