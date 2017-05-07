package DAO;

import Entities.User;

/**
 * Created by linhtran on 06/05/2017.
 */
public interface UserDAO {
    boolean insert(User user);
    boolean delete(int idAuthor);
    boolean update(User user);
}
