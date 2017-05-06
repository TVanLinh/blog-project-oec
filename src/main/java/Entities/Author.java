package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Basic
  @Column(name = "name")
  private String name;

  @Basic
  @Column(name = "pass_word")
  private String passWord;

  @Basic
  @Column(name = "role")
  private int role;

  @OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
  List<Post> postList;

  public Author(String passWord, int role) {
    this.passWord = passWord;
    this.role = role;
  }

  public Author(String name, String passWord, int role) {
    this.name = name;
    this.passWord = passWord;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String password) {
    this.passWord = password;
  }

  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  public List<Post> getPostList() {
    return postList;
  }

  public void setPostList(List<Post> postList) {
    this.postList = postList;
  }

  @Override
  public String toString() {
    return "Author{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", passWord='" + passWord + '\'' +
            ", role=" + role +
            '}';
  }
}
