package entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
@NamedQueries({
        @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person"),
        @NamedQuery(name = "Person.getCount", query = "SELECT COUNT(p) FROM Person p"),
        @NamedQuery(name = "Person.getPerson", query = "SELECT p FROM Person p where p.firstName LIKE :firstName"),
        @NamedQuery(name = "Person.getAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.getByPhone", query = "SELECT per FROM Person per WHERE per.id IN (SELECT pho.person.id FROM Phone pho WHERE pho.number LIKE :number)"),
        @NamedQuery(name = "Person.getCountByHobby", query = "SELECT p FROM Person p WHERE p.hobbies IN (SELECT h FROM Hobby h WHERE h.name LIKE :name)"),
        @NamedQuery(name = "Person.getByCity", query = "SELECT p FROM Person p WHERE (p.address.cityInfo.zipCode LIKE :name) AND (p.address.cityInfo.zipCode LIKE :zip)")
})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Phone> phones;

    @ManyToMany(mappedBy = "persons", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Hobby> hobbies;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    public Person() {
    }

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = new ArrayList<>();
        this.hobbies = new ArrayList<>();
        this.address = new Address();
    }

    public void addPhone(Phone phone) {
        if (phones != null) {
            this.phones.add(phone);
            phone.setPerson(this);
        }
    }

    public void removePhone(Phone phone) {
        if (phones != null) {
            this.phones.remove(phone);
            phone.removePerson(this);
        }
    }

    public void addHobby(Hobby hobby) {
        if (hobby != null) {
            this.hobbies.add(hobby);
            hobby.addPerson(this);
        }
    }

    public void removeHobby(Hobby hobby) {
        if (hobby != null) {
            this.hobbies.remove(hobby);
            hobby.removePerson(this);
        }
    }

    public void setAddress(Address address) {
        if (address != null) {
            this.address = address;
            address.addPerson(this);
        }
    }

    public void removeAddress(Address address) {
        if (address != null) {
            this.address = new Address();
            address.removePerson(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
