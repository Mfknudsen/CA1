package dtos;

import java.util.ArrayList;
import java.util.List;

import entities.Person;

public class PersonDTO {
    private long id;
    private String email;
    private String firstName;
    private String lastName;

    public PersonDTO(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO(Person person) {
        if(person.getId() != null)
            this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }

    public static List<PersonDTO> getDtos(List<Person> person){
        List<PersonDTO> personDTO = new ArrayList<PersonDTO>();
        person.forEach(_person->personDTO.add(new PersonDTO(_person)));
        return personDTO;
    }
}
