package dtos;

import java.util.List;

import entities.CityInfo;
import entities.Person;

public class HobbyDTO {
    private long id;
    private String name;
    private String description;
    private List<Person> persons;

    public HobbyDTO(long id, String name, String description, List<Person> persons) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.persons = persons;
    }
    
}
