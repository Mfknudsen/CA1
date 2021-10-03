package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Hobby")
@NamedQueries({
        @NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE FROM Hobby"),
        @NamedQuery(name = "Hobby.getAll", query = "SELECT h FROM Hobby h")
})
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="wikiLink")
    private String wikiLink;

    @Column(name="type")
    private String type;

    @Column(name="category")
    private String category;

    @ManyToMany
    @JoinTable(name = "person_linked_hobby",
    joinColumns = @JoinColumn(name = "hobby_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private List<Person> persons;

    public Hobby() {}

    public Hobby(String name, String wikiLink, String type, String category) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.type = type;
        this.category = category;
    }
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}

