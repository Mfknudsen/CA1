package facades;

import dtos.HobbyDTO;
import entities.Hobby;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Disabled
public class HobbyFacadeTest {

    private static EntityManagerFactory emf;
    private static HobbyFacade facade;

    public HobbyFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = HobbyFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.persist(new Hobby("hName1", "hURL1", "hType1", "hCat1"));
            em.persist(new Hobby("hName2", "hURL2", "hType2", "hCat2"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreate() throws Exception {
        String expected = "hName";
        String actual = facade.create(new HobbyDTO(new Hobby("hName", "hURL", "hType", "hCategory"))).getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws Exception {
        int expected = 1;
        long actual = facade.getById(1).getId(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCount() throws Exception {
        int expected = 2;
        long actual = facade.getCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        List<HobbyDTO> expected = new ArrayList<>();
        expected.add(new HobbyDTO(new Hobby("hName1", "hURL1", "hType1", "hCat1")));
        expected.add(new HobbyDTO(new Hobby("hName2", "hURL2", "hType2", "hCat2")));
        List<HobbyDTO> actual = facade.getAll();
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
            assertEquals(expected.get(i).getCategory(), actual.get(i).getCategory());
            assertEquals(expected.get(i).getType(), actual.get(i).getType());
            assertEquals(expected.get(i).getWikiLink(), actual.get(i).getWikiLink());
        }
    }
}
