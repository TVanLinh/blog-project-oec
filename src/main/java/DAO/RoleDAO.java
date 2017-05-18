package DAO;

import Entities.Role;

/**
 * Created by linhtran on 08/05/2017.
 */
public interface RoleDAO {
    void insert(Role role);
    void delete(int idAuthor);
    void update(Role role);
    void delete(String userName);
}
