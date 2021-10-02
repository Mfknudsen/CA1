package dtos;

import java.util.List;

import entities.CityInfo;
import entities.Person;

public class AddressDTO {
    private long id;
    private String street;
    private String additionalInfo;
    private List<Person> persons;
    private CityInfo cityInfo;

    public AddressDTO(long id, String street, String additionalInfo, List<Person> persons, CityInfo cityInfo){
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.persons = persons;
        this.cityInfo = cityInfo;
    }
}
