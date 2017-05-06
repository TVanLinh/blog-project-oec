package DAO;

import Entities.Author;
import Entities.Post;

import javax.validation.constraints.Past;

/**
 * Created by linhtran on 06/05/2017.
 */
public interface AuthorDAO {
    boolean insert(Author author);
    boolean delete(int idAuthor);
    boolean update(Author author);
}
