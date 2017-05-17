package DAO;

import Entities.User;

/**
 * Created by linhtran on 06/05/2017.
 */
public interface UserDAO {
    void insert(User user);
    void delete(int idAuthor);
    void update(User user);
}
