package Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by linhtran on 07/05/2017.
 */

@Entity
@Table(name = "user_roles")
public class Role  implements Serializable{

    @Id
    @Column(name = "id_user_roles")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Basic
    private String role;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_name",referencedColumnName = "user_name")
    User user;

    public Role() {
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

}
