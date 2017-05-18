package DAO;

import Entities.Image;

/**
 * Created by linhtran on 09/05/2017.
 */
public interface ImageDAO {
    void insert(Image image);
    void delete(int id);
    void deleteByIdPost(int id);
    void update(Image image);
}
