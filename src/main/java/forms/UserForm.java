package forms;

import entities.User;

/**
 * Created by linhtran on 09/06/2017.
 */
public class UserForm {
    private User user;
    private String rePassWord;

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
}
