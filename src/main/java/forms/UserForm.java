package forms;

import entities.User;

/**
 * Created by linhtran on 09/06/2017.
 */
public class UserForm {
    private User user;
    private String rePassWord;
    private  String newPassWord;

    public UserForm() {
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRePassWord() {
        return rePassWord;
    }

    public void setRePassWord(String rePassWord) {
        this.rePassWord = rePassWord;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }
}
