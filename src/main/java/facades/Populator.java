/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.mysql.cj.xdevapi.Statement;

import utils.EMF_Creator;

/**
 * @author tha
 */
public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        // FacadeExample fe = FacadeExample.getFacadeExample(emf);
        PersonFacade personFacade = PersonFacade.getInstance(emf);

        PersonDTO person1 = personFacade.create(new PersonDTO(new Person("Liliana.Rolfson@example.com", "Libby", "Krajcik")));
        PersonDTO person2 = personFacade.create(new PersonDTO(new Person("Rebecca.Ebert@example.org", "Elza", "Stanton")));
        PersonDTO person3 = personFacade.create(new PersonDTO(new Person("Kip_Erdman66@example.com", "Sherwood", "Goyette")));
        PersonDTO person4 = personFacade.create(new PersonDTO(new Person("Adelbert_Walter@example.org", "Jarod", "O'Kon")));
        PersonDTO person5 = personFacade.create(new PersonDTO(new Person("Bernadette60@example.org", "Anthony", "Block")));

        personFacade.addHobby(person1.getId(), personFacade.getHobbyByName("Blogging"));

        for (PersonDTO dto : new PersonDTO[]{person2, person3}) {
            personFacade.setAddress(dto.getId(), new Address("", ""));
        }

    }

    public static void populateHobbies() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.createQuery("BULK INSERT startcode.Hobby " +
                "FROM '../resources/hobbyScript.sql' " +
                "WITH " +
                "( " +
                "ROWTERMINATOR ='\n' " +
                ")");
    }

    public static void populateZip() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.createQuery("BULK INSERT startcode.CityInfo " +
                "FROM '../resources/zipScript.sql' " +
                "WITH " +
                "( " +
                "ROWTERMINATOR ='\n' " +
                ")");
    }

    public static void main(String[] args) {
        populate();
    }
}
