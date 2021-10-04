package facades;

import dtos.CityInfoDTO;
import entities.CityInfo;
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

public class CityInfoFacadeTest {

    private static EntityManagerFactory emf;
    private static CityInfoFacade facade;

    public CityInfoFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CityInfoFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            em.persist(new CityInfo("4900", "Langt Langt Borte"));
            em.persist(new CityInfo("2850", "Nærum"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetCount() throws Exception {
        int expected = 2;
        long actual = facade.getCityInfoCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        List<CityInfoDTO> expected = new ArrayList<>();
        expected.add(new CityInfoDTO(new CityInfo("2850", "Nærum")));
        expected.add(new CityInfoDTO(new CityInfo("4900", "Langt Langt Borte")));
        List<CityInfoDTO> actual = facade.getAll();
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++){
            assertEquals(expected.get(i).getZipCode(), actual.get(i).getZipCode());
            assertEquals(expected.get(i).getCity(), actual.get(i).getCity());
        }
    }
}
