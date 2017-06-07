package service;

import dao.ImageDAO;
import entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class ImageService extends AbstractDAO<Image> {

    @Autowired
    ImageDAO imageDAO;

    public ImageService() {
    }

    public void delete(int id)
    {
        this.imageDAO.delete(id);
    }

    public  Image find(int id)
    {
        return  this.imageDAO.find(id);
    }

    public  void save(Image image)
    {
        this.imageDAO.update(image);
    }

    public  void deleteByIdPost(int id)
    {
        this.imageDAO.deleteByIdPost(id);
    }
}
