package entities;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by linhtran on 15/06/2017.
 */

@Entity
@Table(name = "configuration")
public class Configuration extends AbstractEntity {

    @Column(name = "name")
    @Basic
    private String name;

    @Column(name = "value")
    @Basic
    private String value;

    public Configuration() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
