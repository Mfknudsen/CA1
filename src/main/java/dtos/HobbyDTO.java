package dtos;

import java.util.List;

import entities.CityInfo;
import entities.Person;

public class HobbyDTO {
    private long id;
    private String street;
    private String additionalInfo;
    private List<Person> persons;
    private CityInfo cityInfo;

    public HobbyDTO(long id, String street, String additionalInfo, List<Person> persons, CityInfo cityInfo){
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.persons = persons;
        this.cityInfo = cityInfo;
    }
    
}
