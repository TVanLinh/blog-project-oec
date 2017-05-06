package DAO;

import Entities.Post;

import javax.validation.constraints.Past;

/**
 * Created by linhtran on 06/05/2017.
 */
public interface PostDAO {
    boolean insert(Post post);
    boolean delete(int idPost);
    boolean update(Post post);
}
