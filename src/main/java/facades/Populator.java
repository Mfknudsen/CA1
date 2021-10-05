/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        // FacadeExample fe = FacadeExample.getFacadeExample(emf);
        PersonFacade personFacade = PersonFacade.getInstance(emf);

        PersonDTO person1 = personFacade.create(new PersonDTO(new Person("Liliana.Rolfson@example.com","Libby","Krajcik")));
        PersonDTO person2 = personFacade.create(new PersonDTO(new Person("Rebecca.Ebert@example.org","Elza","Stanton")));
        PersonDTO person3 = personFacade.create(new PersonDTO(new Person("Kip_Erdman66@example.com","Sherwood","Goyette")));
        PersonDTO person4 = personFacade.create(new PersonDTO(new Person("Adelbert_Walter@example.org","Jarod","O'Kon")));
        PersonDTO person5 = personFacade.create(new PersonDTO(new Person("Bernadette60@example.org","Anthony","Block")));

        // person1.addHobby

    }
    
    public static void main(String[] args) {
        populate();
    }
}
