package com.example.my_library.service;

import com.example.my_library.dto.HomeAdminDTO;
import com.example.my_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeAdminService extends JpaRepository<Book, Long> {
    @Query(value = "select count(*) from users", nativeQuery = true)
    int getNumberOfUsers();

    @Query(value = "select distinct c.name " +
            "from category c, liked l, bookcategories b " +
            "where c.category_id = b.category_id and l.book_id = b.book_id and " +
            "b.book_id = (SELECT book_id FROM liked GROUP BY book_id ORDER BY COUNT(book_id) DESC LIMIT 1)", nativeQuery = true)
    String getMostLikedCategory();

    @Query(value = "select * from book " +
            "where create_at = (select max(create_at) from book) " +
            "limit 1", nativeQuery = true)
    Optional<Book> getRecentAdditionBook();

    @Query(value = "select * from book " +
            "where update_at = (select max(update_at) from book) " +
            "limit 1", nativeQuery = true)
    Optional<Book> getRecentUpdateBook();

    @Query(value = "select distinct b.book_id, b.title, b.author, b.content, b.image, b.description, b.create_at, b.update_at " +
            "from book b, liked l where b.book_id = l.book_id and " +
            "b.book_id = (SELECT book_id FROM liked GROUP BY book_id ORDER BY COUNT(book_id) DESC LIMIT 1)", nativeQuery = true)
    Optional<Book> getMostLikedBook();

    @Query(value = "select distinct b.book_id, b.title, b.author, b.content, b.image, b.description, b.create_at, b.update_at " +
            "from book b, comments c where b.book_id = c.book_id and " +
            "b.book_id = (SELECT book_id FROM comments GROUP BY book_id ORDER BY COUNT(book_id) DESC LIMIT 1)", nativeQuery = true)
    Optional<Book> getMostCommentsBook();
}
