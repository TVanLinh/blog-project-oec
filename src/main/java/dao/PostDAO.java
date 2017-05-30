package dao;

import entities.Post;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by linhtran on 06/05/2017.
 */
public interface PostDAO {
    void delete(int idPost);
    void update(Post post);
     Post find(int id) ;
     List<Post> getAllPost() ;

     List<Post> getAllPost(String query);

     List<Post> getAllPostPublic() ;

     List<Post> getPost(int from, int limit) ;

     List<Post> getPostByIdUser(int idUser, int from, int limit) ;


     List<Post> getPostByIdUser(int idUser) ;
      List<BigInteger> getStatisticByMonth();
}
