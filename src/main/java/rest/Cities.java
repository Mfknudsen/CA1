package rest;

import dtos.CityInfoDTO;
import facades.CityInfoFacade;

import javax.ws.rs.*;
import java.util.List;

@Path("/cities")
public class Cities {

    private static final CityInfoFacade cityFacade = CityInfoFacade.getInstance(Endpoints.EMF);

    @GET
    @Produces("application/json")
    public String cityBase() {
        List<CityInfoDTO> cities = cityFacade.getAll();
        return Endpoints.GSON.toJson(cities);
    }
}