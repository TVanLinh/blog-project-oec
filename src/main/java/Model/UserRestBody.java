package Model;

import Entities.Post;

import java.util.List;

import JsonViews.Views;
import com.fasterxml.jackson.annotation.JsonView;
/**
 * Created by linhtran on 10/05/2017.
 */


public class UserRestBody {

    @JsonView(Views.Public.class)
    private String msg;

    @JsonView(Views.Public.class)
    private  int id;

    @JsonView(Views.Public.class)
    private String code;

    @JsonView(Views.Public.class)
    private  int numberLike;

    @JsonView(Views.Public.class)
    private  String statusImg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void getId(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(int numberLike) {
        this.numberLike = numberLike;
    }

    public String getStatusImg() {
        return statusImg;
    }

    public void setStatusImg(String statusImg) {
        this.statusImg = statusImg;
    }
}
