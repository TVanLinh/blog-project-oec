package Entities;

import com.mysql.fabric.xmlrpc.base.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

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
  private String status;

  @Basic
  @Column(name = "approve")
  private String approve;

  @Basic
  @Column(name = "approved_time")
  private Date approvedTime;

  @Basic
  @Column(name = "update_time")
  private Date updateTime;

  @Basic
  @Column(name = "person_updated")
  private int personUpdated;

  @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
  @JoinColumn(name = "id_author")
  Author author;

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getApprove() {
    return approve;
  }

  public void setApprove(String approve) {
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

  public int getPersonUpdated() {
    return personUpdated;
  }

  public void setPersonUpdated(int personUpdated) {
    this.personUpdated = personUpdated;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }
}
