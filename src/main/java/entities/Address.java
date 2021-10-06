package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Address")
@NamedQueries({
        @NamedQuery(name = "Address.deleteAllRows", query = "DELETE FROM Address"),
        @NamedQuery(name = "Address.getCount", query = "SELECT COUNT(a) FROM Address a"),
        @NamedQuery(name = "Address.getByStreet", query = "SELECT a FROM Address a WHERE a.street LIKE :street"),
        @NamedQuery(name = "Address.getAll", query = "SELECT a FROM Address a")
})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="street")
    private String street;

    @Column(name="additionalInfo")
    private String additionalInfo;

    @OneToMany(mappedBy = "address", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    private List<Person> persons;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private CityInfo cityInfo;

    public Address() {}

    public Address(String street, String additionalInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.persons = new ArrayList<>();
        this.cityInfo = new CityInfo();
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
    }
    
    public void setCityInfo(CityInfo cityInfo) {
        if (cityInfo != null) {
            this.cityInfo = cityInfo;
            cityInfo.addAddress(this);
        }
    }

    public void removeCityInfo(CityInfo cityInfo) {
        if (cityInfo != null) {
            this.cityInfo = new CityInfo();
            cityInfo.removeAddress(this);
        }
    }
    
    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}



