package facades;

import dtos.PhoneDTO;
import entities.Phone;
import utils.EMF_Creator;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PhoneFacadeTest {

    private static EntityManagerFactory emf;
    private static PhoneFacade facade;

    public PhoneFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PhoneFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.persist(new Phone("1234567890", "Tilhøre M"));
            em.persist(new Phone("0987654321", "Tilhøre A"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method
    @Test
    public void testCreate() throws Exception {
        String expected = "123454321";
        String actual = new PhoneDTO(new Phone("123454321", "test")).getNumber();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByID() throws Exception {
        int expected = 1;
        long actual = facade.getById(1).getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCount() throws Exception {
        int expected = 2;
        long actual = facade.getPhoneCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        List<PhoneDTO> expected = new ArrayList<>();
        expected.add(new PhoneDTO(new Phone("0987654321", "Tilhøre A")));
        expected.add(new PhoneDTO(new Phone("1234567890", "Tilhøre M")));
        List<PhoneDTO> actual = facade.getAll();
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getNumber(), actual.get(i).getNumber());
            assertEquals(expected.get(i).getDescription(), actual.get(i).getDescription());
        }
    }
}
