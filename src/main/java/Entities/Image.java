package Entities;

import JsonViews.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Created by linhtran on 09/05/2017.
 */

@Entity
@Table(name = "postimage")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Basic
    @Column(name = "link")
    @JsonView(Views.Public.class)
    private String link;

    @Basic
    @Column(name = "alt")
    @JsonView(Views.Public.class)
    private  String alt;

    @Basic
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_post",referencedColumnName = "id")
    private Post post;

    public Image() {
    }

    public Image(String link, String alt) {
        this.link = link;
        this.alt = alt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", alt='" + alt + '\'' +
                '}';
    }
}
