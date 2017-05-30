package dao;

import entities.User;

/**
 * Created by linhtran on 06/05/2017.
 */
public interface UserDAO {
    void delete(int idAuthor);
    void update(User user);
}
