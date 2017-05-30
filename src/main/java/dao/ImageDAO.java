package dao;

import entities.Image;

import java.util.List;

/**
 * Created by linhtran on 09/05/2017.
 */
public interface ImageDAO {
    void delete(int id);
    void deleteByIdPost(int id);
    void update(Image image);

     Image find(int id);

     List<Image> getAllImage() ;


     Image getImageByName(String name);

}
