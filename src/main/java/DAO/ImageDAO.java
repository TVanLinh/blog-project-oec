package DAO;

import Entities.Image;
import Entities.User;

/**
 * Created by linhtran on 09/05/2017.
 */
public interface ImageDAO {
    boolean insert(Image image);
    boolean delete(int id);
    boolean update(Image image);
}
