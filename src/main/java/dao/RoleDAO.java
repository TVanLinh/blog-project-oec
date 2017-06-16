package dao;

import entities.Role;

import java.util.List;

/**
 * Created by linhtran on 08/05/2017.
 */
public interface RoleDAO {

    void delete(int idAuthor);

    void update(Role role);

    void delete(String userName);

    List<Role> getRoleByUsername(String username);

    List<Role> getRoleByUserId(int idUser);

    void deleteByUserId(int id);
}
