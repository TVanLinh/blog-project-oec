package entities;

import com.fasterxml.jackson.annotation.JsonView;
import jsonviews.Views;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by linhtran on 07/05/2017.
 */

@Entity
@Table(name = "user_roles")
public class Role extends AbstractEntity  implements GrantedAuthority {

    @Basic
    @JsonView(Views.Public.class)
    private String role;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user",referencedColumnName = "id")
    User user;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuthority() {
        return this.getRole();
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }
}
