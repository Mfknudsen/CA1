package api;

import com.google.gson.Gson;
import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;

@Path("/users")
public class user {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @GET
    @Produces("application/json")
    public String hello() {
       return "HELLO WORLD";
       
        EntityManager em =  emf.createEntityManager();

        try {
            TypedQuery<Person> query = em.createQuery("");
            List<Person> users = query.getResultList();
            return new Gson().toJson(users);
        } finally {
            em.close();
        }
    }
}