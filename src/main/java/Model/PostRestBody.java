package Model;

import Entities.Post;
import JsonViews.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * Created by linhtran on 10/05/2017.
 */

public class PostRestBody {
    @JsonView(Views.Public.class)
    private  String msg;

    @JsonView(Views.Public.class)
    private  int numberPage;

    @JsonView(Views.Public.class)
    int numberConf;

    @JsonView(Views.Public.class)
    int numberApprove;

    @JsonView(Views.Public.class)
    List<Post> posts;

    @JsonView(Views.Public.class)
    private  int totalPost;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getNumberConf() {
        return numberConf;
    }

    public void setNumberConf(int numberConf) {
        this.numberConf = numberConf;
    }

    public int getNumberApprove() {
        return numberApprove;
    }

    public void setNumberApprove(int numberApprove) {
        this.numberApprove = numberApprove;
    }

    public int getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(int totalPost) {
        this.totalPost = totalPost;
    }
}
