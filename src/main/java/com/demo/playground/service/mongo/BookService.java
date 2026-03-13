package com.demo.playground.service.mongo;

import com.demo.playground.entity.mongo.Book;
import com.demo.playground.repository.mongo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book updateBook(String id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setPrice(updatedBook.getPrice());
                    existingBook.setYearPublished(updatedBook.getYearPublished());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
