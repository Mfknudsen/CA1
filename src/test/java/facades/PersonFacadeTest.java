package facades;

import dtos.PersonDTO;
import entities.Person;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(new Person("madsMail", "Mads", "M"));
            em.persist(new Person("albertMail", "Albert", "A"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetByID() throws Exception {
        int id = 1;
        PersonDTO personDTO = facade.getById(id);
        assertEquals(id, personDTO.getId());
    }

    @Test
    public void testGetPersonCount() throws Exception {
        int expected = 2;
        Long actual = facade.getCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        List<PersonDTO> expected = new ArrayList<>();
        expected.add(new PersonDTO(new Person("madsMail", "Mads", "M")));
        expected.add(new PersonDTO(new Person("albertMail", "Albert", "A")));
        List<PersonDTO> actual = facade.getAll();
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getEmail(), actual.get(i).getEmail());
            assertEquals(expected.get(i).getFirstName(), actual.get(i).getFirstName());
            assertEquals(expected.get(i).getLastName(), actual.get(i).getLastName());
        }
    }
}
