package entities;

import com.fasterxml.jackson.annotation.JsonView;
import jsonviews.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractEntity implements Serializable {


  @Basic
  @Column(name = "user_name",unique = true)
  @JsonView(Views.Public.class)
  private String userName;

  @Basic
  @Column(name = "pass_word")
  @JsonView(Views.Public.class)
  private String passWord;

  @Basic
  @Column(name = "enabled")
  private int  enabled=1;

  @OrderBy(value = "id asc")
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER,targetEntity = Role.class)
  @JsonView(Views.Public.class)
  List<Role> roleList;

  @OrderBy(value = "id asc")
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Post.class)
  List<Post> postList;

  public User() {
  }

  public User(String userName, String passWord, int enabled) {
    this.userName = userName;
    this.passWord = passWord;
    this.enabled = enabled;
  }

  public User(String passWord) {
    this.passWord = passWord;
  }

  public User(String name, String passWord) {
    this.userName=name;
    this.passWord = passWord;
  }

  public User(String userName, String passWord, int enabled, List<Role> roleList, List<Post> postList) {
    this.userName = userName;
    this.passWord = passWord;
    this.enabled = enabled;
    this.roleList = roleList;
    this.postList = postList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String name) {
    this.userName=name;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String password) {
    this.passWord = password;
  }

  public int getEnabled() {
    return enabled;
  }

  public void setEnabled(int enabled) {
    this.enabled = enabled;
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
    for(Role role:roleList)
    {
      role.setUser(this);
    }
  }

  public List<Post> getPostList() {
    return postList;
  }

  public void setPostList(List<Post> postList) {
    this.postList = postList;
    for(Post post:postList)
    {
      post.setUser(this);
    }
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + userName + '\'' +
            ", passWord='" + passWord + '\'' +
            '}';
  }
}
