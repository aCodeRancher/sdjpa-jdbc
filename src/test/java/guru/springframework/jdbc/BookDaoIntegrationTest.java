package guru.springframework.jdbc;

import guru.springframework.jdbc.dao.BookDao;
import guru.springframework.jdbc.dao.BookDaoImpl;
import guru.springframework.jdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {
    @Autowired
    BookDao bookDao;

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("5710");
        book.setPublisher("Spring Framework Guru");
        book.setTitle("Hibernate and JPA");
        book.setAuthorId(3L);
        Book saved = bookDao.saveNewBook(book);
        bookDao.deleteBookById(saved.getId());
        Book deleted = bookDao.getById(saved.getId());
        assertThat(deleted).isNull();
    }

    @Test
    void testUpdateBook() {
        Book book = new Book();
        book.setIsbn("5710");
        book.setTitle("Hibernate and JPA: Beginner to Guru");
        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("Hibernate and Spring Data JPA: Beginner to Guru");
        Book updated =bookDao.updateBook(saved);

        assertThat(updated.getTitle()).isEqualTo("Hibernate and Spring Data JPA: Beginner to Guru");
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setTitle("Apache Maven Beginner to Guru");
        book.setIsbn("5711");
        book.setPublisher("Spring Framework Guru");
        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByTitle() {
        Book book = bookDao.findBookByTitle("Domain-Driven Design");

        assertThat(book).isNotNull();
    }

    @Test
    void testGetBook() {

        Book book = bookDao.getById(1L);

        assertThat(book).isNotNull();

    }

}
