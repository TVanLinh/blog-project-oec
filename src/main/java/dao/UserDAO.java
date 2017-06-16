package dao;

import entities.User;

import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */
public interface UserDAO {

    void delete(int idAuthor);

    void update(User user);

    User find(int id);

    User getUserByName(String name);

    List<User> getAllUser(String query);

    List<User> getAllUser();

}
