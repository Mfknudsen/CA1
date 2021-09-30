package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import java.util.List;

@Entity
@Table(name = "Address")
@NamedQueries({
        @NamedQuery(name = "Address.deleteAllRows", query = "DELETE FROM Address"),
        @NamedQuery(name = "Address.getAll", query = "SELECT a FROM Address a")
})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="Street")
    private String Street;

    @Column(name="additionalInfo")
    private String additionalInfo;

    @OneToMany(mappedBy = "address", cascade = CascadeType.PERSIST)
    private List<Person> persons;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityInfo cityInfo;

    public Address() {}

    public Address(String Street, String additionalInfo) {
        this.Street = Street;
        this.additionalInfo = additionalInfo;
    }
    
    public long getId() {
        return id;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}


