/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;
import utils.ScriptRunner;

/**
 * @author tha
 */
public class Populator {
    public static void populate() throws FileNotFoundException, SQLException, IOException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        // FacadeExample fe = FacadeExample.getFacadeExample(emf);
        PersonFacade personFacade = PersonFacade.getInstance(emf);

        PersonDTO person1 = personFacade.create(new PersonDTO(new Person("Liliana.Rolfson@example.com", "Libby", "Krajcik")));
        PersonDTO person2 = personFacade.create(new PersonDTO(new Person("Rebecca.Ebert@example.org", "Elza", "Stanton")));
        PersonDTO person3 = personFacade.create(new PersonDTO(new Person("Kip_Erdman66@example.com", "Sherwood", "Goyette")));
        PersonDTO person4 = personFacade.create(new PersonDTO(new Person("Adelbert_Walter@example.org", "Jarod", "O'Kon")));
        PersonDTO person5 = personFacade.create(new PersonDTO(new Person("Bernadette60@example.org", "Anthony", "Block")));

        populateHobby();
        populateCityInfo();

        personFacade.addHobby(person1.getId(), personFacade.getHobbyByName("Blogging"));

        for (PersonDTO dto : new PersonDTO[]{person2, person3}) {
            personFacade.setAddress(dto.getId(), new Address("", ""));
        }

    }

    public static void populateHobby() throws SQLException, FileNotFoundException, IOException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/startcode?serverTimezone=UTC", "dev", "ax2");
        ScriptRunner runner = new ScriptRunner(conn, false, true);
        Path path = Paths.get("./src/main/java/resources/hobbyScript.sql");
        runner.runScript(new BufferedReader(new FileReader(path.toAbsolutePath().toString())));

    }

    public static void populateCityInfo() throws SQLException, FileNotFoundException, IOException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/startcode?serverTimezone=UTC", "dev", "ax2");
        ScriptRunner runner = new ScriptRunner(conn, false, true);
        Path path = Paths.get("./src/main/java/resources/zipScript.sql");
        runner.runScript(new BufferedReader(new FileReader(path.toAbsolutePath().toString())));

    }

    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
        populate();
    }
}
