package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.PersonDTO;
import entities.Person;
import facades.PersonFacade;
import utils.EMF_Creator;
import utils.Utility;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/users")
public class Endpoints {

    public static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade personFacade = PersonFacade.getInstance(EMF);
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String base() {
        List<PersonDTO> persons = personFacade.getAll();

        return GSON.toJson(persons);
    }

    @Path("byID/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByID(@PathParam("id") long id) {
        PersonDTO person = personFacade.getById(id);
        return GSON.toJson(person);
    }

    @Path("byPhone/{phone}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByPhone(@PathParam("phone") int number) {
        List<PersonDTO> list = personFacade.getSpecific("Phone", "" + number);
        PersonDTO person = list.get(0);
        return GSON.toJson(person);
    }

    @Path("byHobby/{hobby}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByHobby(@PathParam("hobby") String name) {
        List<PersonDTO> persons = personFacade.getSpecific("Hobby", name);
        return GSON.toJson(persons.size());
    }

    @Path("byCity/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByCity(@PathParam("name") String name) {
        List<PersonDTO> persons = personFacade.getSpecific("City", name);
        return GSON.toJson(persons);
    }

    @Path("edit/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editPerson(String jsonString) throws Exception {

        PersonDTO personDTO = Utility.json2DTO(jsonString);
        personFacade.Edit(personDTO);
    }
}
