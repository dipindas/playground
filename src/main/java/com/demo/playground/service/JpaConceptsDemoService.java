package com.demo.playground.service;

import com.demo.playground.entity.association.Author;
import com.demo.playground.entity.association.Book;
import com.demo.playground.repository.jpa.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Demonstrates Core JPA Concepts:
 * 1. Entity Lifecycle & States (New, Managed, Detached, Removed)
 * 2. Persistence Context (First-Level Cache)
 * 3. Dirty Checking
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JpaConceptsDemoService {

    private final AuthorRepository authorRepository;

    // Injecting the EntityManager gives direct access to the Persistence Context.
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Demonstrates Entity States and the Persistence Context.
     */
    @Transactional
    public String demonstrateLifecycleAndPersistenceContext() {
        StringBuilder sb = new StringBuilder();

        // 1. NEW (Transient) State
        // The object is created but not yet associated with the Persistence Context.
        Author author = new Author();
        author.setName("Stephen King");
        sb.append("1. Author created (New/Transient state). \n");

        // 2. MANAGED (Persistent) State
        // persist() attaches the entity to the Persistence Context.
        // It will be saved to the DB upon transaction commit (or flush).
        entityManager.persist(author);
        sb.append("2. entityManager.persist() called (Managed state). ID assigned: ").append(author.getId()).append("\n");

        // First-Level Cache Demonstration:
        // Retrieving the entity by ID will not trigger a SELECT query because it's in the Persistence Context.
        Author cachedAuthor = entityManager.find(Author.class, author.getId());
        sb.append("3. Retrieved author via entityManager.find(). Is it the exact same object reference? ")
          .append(author == cachedAuthor).append("\n");

        // 3. DETACHED State
        // detach() removes the entity from the Persistence Context.
        // Changes made to it will NOT be synced to the database.
        entityManager.detach(author);
        author.setName("Stephen King - Detached Change");
        sb.append("4. entityManager.detach() called (Detached state). Modification made to name, but it won't be saved automatically.\n");

        // To re-attach, we would use merge() (not demonstrated here to preserve the flow).
        // Let's load it back from the DB to see the original state (requires a flush/clear to force DB hit,
        // but since we detached the only reference, find() will hit the DB if we flushed before.
        // Since we didn't flush before detaching, the DB doesn't have it yet!
        // Wait, if we detach before flush, the INSERT might not happen. Let's merge it back to be safe.)

        Author managedAuthor = entityManager.merge(author);
        sb.append("5. entityManager.merge() called. Entity is Managed again.\n");

        // 4. REMOVED State
        // The entity is scheduled for deletion from the DB.
        entityManager.remove(managedAuthor);
        sb.append("6. entityManager.remove() called (Removed state). Entity will be deleted on commit.\n");

        return sb.toString();
    }

    /**
     * Demonstrates Dirty Checking.
     * JPA automatically detects modifications to Managed entities and issues an UPDATE statement
     * when the transaction commits, without requiring an explicit save() call.
     */
    @Transactional
    public String demonstrateDirtyChecking() {
        // Setup: Create and save an author directly
        Author author = new Author();
        author.setName("J.K. Rowling");
        author = authorRepository.saveAndFlush(author); // Flushes to DB immediately

        Long authorId = author.getId();

        // Now, we retrieve the entity. It is in MANAGED state.
        Author managedAuthor = authorRepository.findById(authorId).orElseThrow();

        // We modify the entity.
        // Because it is managed, the Persistence Context tracks these changes.
        managedAuthor.setName("J.K. Rowling (Updated)");

        // Notice: We do NOT call authorRepository.save(managedAuthor) here!
        // When the @Transactional method finishes, Hibernate will perform 'Dirty Checking',
        // find that 'name' has changed, and automatically execute an UPDATE SQL statement.

        return "Author retrieved and modified. The new name '" + managedAuthor.getName() +
               "' will be saved to the database automatically due to Dirty Checking at transaction commit.";
    }

    /**
     * Demonstrates Cascading in an Association (@OneToMany).
     */
    @Transactional
    public String demonstrateAssociationsAndCascading() {
        Author author = new Author();
        author.setName("George R. R. Martin");

        Book book1 = new Book();
        book1.setTitle("A Game of Thrones");

        Book book2 = new Book();
        book2.setTitle("A Clash of Kings");

        // Bidirectional synchronization
        author.addBook(book1);
        author.addBook(book2);

        // Because of CascadeType.ALL on the @OneToMany relationship,
        // saving the Author will automatically persist Book1 and Book2.
        authorRepository.save(author);

        return "Author and 2 Books were created. Saving the Author automatically cascaded the persist operation to the Books because of CascadeType.ALL.";
    }
}
