package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "Phone")
@NamedQueries({
        @NamedQuery(name = "Phone.deleteAllRows", query = "DELETE FROM Phone"),
        @NamedQuery(name = "Phone.getCount", query = "SELECT COUNT(p) FROM Phone p"),
        @NamedQuery(name = "Phone.getAll", query = "SELECT p FROM Phone p")
})
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="number")
    private String number;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Phone() {}

    public Phone(String number, String description) {
        this.number = number;
        this.description = description;
        this.person = new Person();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void removePerson(Person person) {
        if(this.person.equals(person))
            this.person = new Person();

    }
    
    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


