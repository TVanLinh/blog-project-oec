package DAO;

import Entities.Post;

/**
 * Created by linhtran on 06/05/2017.
 */
public interface PostDAO {
    void insert(Post post);
    void delete(int idPost);
    void update(Post post);
}
