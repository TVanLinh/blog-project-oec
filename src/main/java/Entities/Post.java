package Entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "post")
public class Post  implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

//  @NotEmpty
//  @Size(min = 10,max = 100,message = "Min length 10.!")
  @Basic
  @Column(name = "title")
  private String title;

  @Basic
  @Column(name = "content")
  private String content;

  @Basic
  @Column(name = "number_view")
  private int numberView;

  @Basic
  @Column(name = "number_like")
  private int numberLike;

  @Basic
  @Column(name = "status")
  private int status;

  @Basic
  @Column(name = "approve")
  private int approve;

  @Basic
  @Column(name = "approved_time")
  private Date approvedTime;

  @Basic
  @Column(name = "update_time")
  private Date updateTime;

  @Basic
  @Column(name = "user_updated")
  private String userUpdated;

  @Basic
  @Column(name = "time_post")
  private Date timePost;

  @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
  @JoinColumn(name = "id_user",referencedColumnName = "id")
  User user;
//    @Basic
//    @Column(name = "user_name")
////    private String user_name;

    public Post() {
    }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getNumberView() {
    return numberView;
  }

  public void setNumberView(int numberView) {
    this.numberView = numberView;
  }

  public int getNumberLike() {
    return numberLike;
  }

  public void setNumberLike(int numberLike) {
    this.numberLike = numberLike;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getApprove() {
    return approve;
  }

  public void setApprove(int approve) {
    this.approve = approve;
  }

  public Date getApprovedTime() {
    return approvedTime;
  }

  public void setApprovedTime(Date approvedTime) {
    this.approvedTime = approvedTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getUserUpdated() {
    return userUpdated;
  }

  public void setUserUpdated(String  userUpdated) {
    this.userUpdated = userUpdated;
  }

//  public String getUser_name() {
//    return user_name;
//  }
//
//  public void setUser_name(String user_name) {
//    this.user_name = user_name;
//  }
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getTimePost() {
    return timePost;
  }

  public void setTimePost(Date timePost) {
    this.timePost = timePost;
  }
}
