package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;

/**
 * Created by jt on 8/20/21.
 */
public interface AuthorDao {

    Author getById(Long id);

    Author getByName(String firstName, String lastName);
}
