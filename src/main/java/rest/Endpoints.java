package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.Person;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/users")
public class Endpoints {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade personFacade = PersonFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("application/json")
    public String base() {
        List<PersonDTO> persons = personFacade.getAll();

        return GSON.toJson(persons);
    }

    @Path("byID/{id}")
    @GET
    @Produces("application/json")
    public String getByID(@PathParam("id") long id) {
        PersonDTO person = personFacade.getById(id);
        return GSON.toJson(person);
    }

    @Path("byPhone/{phone}")
    @GET
    @Produces("application/json")
    public String getByPhone(@PathParam("phone") int number) {
        System.out.println("\n" + number + "\n");
        List<PersonDTO> list = personFacade.getSpecific("Phone", "" + number);
        PersonDTO person = list.get(0);
        return GSON.toJson(person);
    }

    @Path("byHobby/{hobby}")
    @GET
    @Produces("application/json")
    public String getByHobby(@PathParam("hobby") String name) {
        List<PersonDTO> persons = personFacade.getAll();

        return GSON.toJson(persons.size());
    }

    @Path("byCity/{name}")
    @GET
    @Produces("application/json")
    public String getByCity(@PathParam("name") String name) {
        return "[user, user, ...]";
    }
}
