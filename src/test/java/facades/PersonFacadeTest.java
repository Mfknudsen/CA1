package facades;

import dtos.PersonDTO;
import edu.emory.mathcs.backport.java.util.Collections;
import entities.Person;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person person1, person2;

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
        person1 = new Person("Herta_Jenkins@example.org", "Julian", "Reilly");
        person2 = new Person("Myrtle47@example.net", "Eleonore", "Predovic");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(person1);
            em.persist(person2);
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
        long id = person1.getId();
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
        List<Person> expected1 = new ArrayList<>();
        expected1.add(person1);
        expected1.add(person2);
        List<PersonDTO> expected2 = PersonDTO.getDtos(expected1);
        List<PersonDTO> actual = facade.getAll();
        assertEquals(expected2.size(), actual.size());
        assertEquals(expected1.size(), actual.size());

        Comparator comparator = (Comparator<PersonDTO>) (o1, o2) -> (int) (o1.getId() - o2.getId());

        Collections.sort(expected2, comparator);
        Collections.sort(actual, comparator);


        for (int i = 0; i < expected2.size(); i++){
            assertEquals(expected2.get(i).getEmail(), actual.get(i).getEmail());
            assertEquals(expected2.get(i).getFirstName(), actual.get(i).getFirstName());
            assertEquals(expected2.get(i).getLastName(), actual.get(i).getLastName());
        }
    }
}
