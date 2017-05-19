package Entities;

import JsonViews.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post  implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @JsonView(Views.Public.class)
  private int id;

//  @NotEmpty
//  @Size(min = 10,max = 100,message = "Min length 10.!")
  @Basic
  @Column(name = "title")
  @JsonView(Views.Public.class)
  private String title;

  @Basic
  @Column(name = "content")
  private String content;

  @Basic
  @Column(name = "number_view")
  @JsonView(Views.Public.class)
  private int numberView;

  @Basic
  @Column(name = "number_like")
  @JsonView(Views.Public.class)
  private int numberLike;

  @Basic
  @Column(name = "status")
  @JsonView(Views.Public.class)
  private int status;

  @Basic
  @Column(name = "approve")
  @JsonView(Views.Public.class)
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
  @JsonView(Views.Public.class)
  private Date timePost;

  @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
  @JoinColumn(name = "id_user",referencedColumnName = "id")
  @JsonView(Views.Public.class)
  User user;

  @OneToOne(mappedBy = "post",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JsonView(Views.Public.class)
  private Image image;

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

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
    this.image.setPost(this);
  }
}
