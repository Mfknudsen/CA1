package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/users")
public class Endpoints {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade personFacade = PersonFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("application/json")
    public String base() {
        return "[user, user, ...]";
    }

    @Path("byID/{id}")
    @GET
    @Produces("application/json")
    public String getByID(@PathParam("id") int id) {
        return "user";
    }

    @Path("byPhone/{phone}")
    @GET
    @Produces("application/json")
    public String getByPhone(@PathParam("phone") int number) {
        return "user";
    }

    @Path("byHobby/{hobby}")
    @GET
    @Produces("application/json")
    public String getByHobby(@PathParam("hobby") String name) {
        return "count";
    }

    @Path("byCity/{name}")
    @GET
    @Produces("application/json")
    public String getByCity(@PathParam("name") String name) {
        return "[user, user, ...]";
    }
}
