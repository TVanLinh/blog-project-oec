package DAO;

import Entities.Role;
import Entities.User;

/**
 * Created by linhtran on 08/05/2017.
 */
public interface RoleDAO {
    boolean insert(Role role);
    boolean delete(int idAuthor);
    boolean update(Role role);
}
