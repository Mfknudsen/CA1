package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import java.util.List;

@Path("/users")
public class Endpoints {

    public static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade personFacade = PersonFacade.getInstance(EMF);
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

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
        List<PersonDTO> list = personFacade.getSpecific("Phone", "" + number);
        PersonDTO person = list.get(0);
        return GSON.toJson(person);
    }

    @Path("byHobby/{hobby}")
    @GET
    @Produces("application/json")
    public String getByHobby(@PathParam("hobby") String name) {
        List<PersonDTO> persons = personFacade.getSpecific("Hobby", name);
        return GSON.toJson(persons.size());
    }

    @Path("byCity/{name}")
    @GET
    @Produces("application/json")
    public String getByCity(@PathParam("name") String name) {
        List<PersonDTO> persons = personFacade.getSpecific("City", name);
        return GSON.toJson(persons);
    }
}
