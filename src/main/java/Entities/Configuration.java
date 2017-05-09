package Entities;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
public class Configuration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Basic
  @Column(name = "web_title")
  private String webTitle;

  @Column(name = "date_format")
  private String dateFormat;

  @Column(name = "number_view_post")
  private int numberViewPost;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getWebTitle() {
    return webTitle;
  }

  public void setWebTitle(String webTitle) {
    this.webTitle = webTitle;
  }

  public String getDateFormat() {
    return dateFormat;
  }

  public void setDateFormat(String dateFormat) {
    this.dateFormat = dateFormat;
  }

  public int getNumberViewPost() {
    return numberViewPost;
  }

  public void setNumberViewPost(int numberViewPost) {
    this.numberViewPost = numberViewPost;
  }

  @Override
  public String toString() {
    return "Configuration{" +
            "id=" + id +
            ", webTitle='" + webTitle + '\'' +
            ", dateFormat='" + dateFormat + '\'' +
            ", numberViewPost=" + numberViewPost +
            '}';
  }
}
