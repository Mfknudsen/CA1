package dtos;

import java.util.List;

import entities.Address;
import entities.Hobby;

public class PersonDTO {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<Phone> phones;
    private List<Hobby> hobbies;
    private Address address;

    public PersonDTO(long id, String email, String firstName, String lastName,
    List<Phone> phones,List<Hobby> hobbies, Address address) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
        this.hobbies = hobbies;
        this.address = address;
    }
}
