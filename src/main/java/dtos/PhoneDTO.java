package dtos;

import entities.Person;

public class PhoneDTO {
    private long id;
    private String number;
    private String description;
    private Person person;

    public PhoneDTO(long id, String number, String description, Person person) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.person = person;
    }
}
