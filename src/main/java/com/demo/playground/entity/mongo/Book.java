package com.demo.playground.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * MongoDB document representing a Book.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private String author;

    private BigDecimal price;

    private int yearPublished;
}
