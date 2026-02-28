package com.demo.playground.entity.association;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates @OneToMany association.
 * The 'mappedBy' attribute tells Hibernate that 'Author' does not own the relationship.
 * The 'Book' entity owns the foreign key in the database.
 */
@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // mappedBy indicates that the 'author' field in Book is the owning side.
    // CascadeType.ALL ensures that operations (persist, remove) cascade to Book entities.
    // orphanRemoval = true removes Book when removed from the collection.
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    // Utility methods to maintain bidirectional relationship synchronization
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
}
