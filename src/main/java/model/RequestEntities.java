package model;

/**
 * Created by linhtran on 12/06/2017.
 */
public class RequestEntities {
    private String msg;
    private int page;

    public RequestEntities() {
    }

    public RequestEntities(String msg, int page) {
        this.msg = msg;
        this.page = page;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
