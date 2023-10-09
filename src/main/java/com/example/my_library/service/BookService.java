package com.example.my_library.service;


import com.example.my_library.model.Book;
import com.example.my_library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookService extends JpaRepository<Book, Long>{
    @Query(value = "select b.book_id, b.author, b.title, b.content, b.image, b.description, b.create_at, b.update_at " +
            "from book b, category c, bookcategories bc " +
            "where b.book_id = bc.book_id and bc.category_id = c.category_id and " +
            "(c.name like concat('%',lower(:category), '%') and b.title like concat('%',lower(:title), '%'))", nativeQuery = true)
    List<Book> findByTitleOrCategoryIgnoreCase(@Param("category") String category, @Param("title") String title);

    Optional<Book> findById(Long id);

    @Query(value = "SELECT COUNT(*) FROM liked l WHERE l.book_id = ?1", nativeQuery = true)
    int getTotalLikesByBookId(Long bookId);

    @Query(value = "SELECT COUNT(*) FROM comments c WHERE c.book_id = ?1", nativeQuery = true)
    int getTotalCommentsByBookId(Long bookId);

    @Query(value = "SELECT name FROM my_library.category c, my_library.bookcategories bc, my_library.book b " +
            "where bc.book_id = b.book_id and c.category_id = bc.category_id and b.book_id = ?1", nativeQuery = true)
    List<String> getCategoriesByBookId(Long bookId);
}