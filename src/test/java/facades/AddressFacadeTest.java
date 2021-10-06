package facades;

import utils.EMF_Creator;
import entities.Address;
import dtos.AddressDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class AddressFacadeTest {

    private static EntityManagerFactory emf;
    private static AddressFacade facade;

    public AddressFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = AddressFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.persist(new Address("Schulist Bridge", "10"));
            em.persist(new Address("Aida Well", "8920"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void create() throws Exception {
        String expected = "Aufderhar Mews";
        String actual = facade.create(new AddressDTO(new Address("Aufderhar Mews", "200"))).getStreet();
        assertEquals(expected, actual);
    }

    @Test
    public void getById() throws Exception{
        int id = 1;
        String expected = "Aida Well";
        String actual = facade.getById(id).getStreet();
        assertEquals(expected, actual, "Expects address from id =" + id + "  should be" + expected);
    }

    @Test
    public void getByStreet() throws Exception{
        String expected = "10";
        String actual = facade.getSpecific("Schulist Bridge").get(0).getAdditionalInfo();
        assertEquals(expected, actual, "Expects first address by street to have additional info of 10");
    }

    @Test
    public void getAddressCount() throws Exception{
        assertEquals(2, facade.getCount(), "Expects two rows in the database");
    }

    @Test
    public void getAll() throws Exception{
        List<AddressDTO> expected = new ArrayList<AddressDTO>();
        expected.add(new AddressDTO(new Address("Aida Well", "8920")));
        expected.add(new AddressDTO(new Address("Schulist Bridge", "10")));
        assertEquals(expected.get(0).getStreet(), facade.getAll().get(0).getStreet(), "Expects first address with street name = Aufderhar Spur");
        assertEquals(expected.get(1).getStreet(), facade.getAll().get(1).getStreet(), "Expects second address with street name = Simonis Ports");
    }

}
