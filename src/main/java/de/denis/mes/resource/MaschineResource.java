package de.denis.mes.resource;

import de.denis.mes.entity.Maschine;
import de.denis.mes.entity.MaschinenStatus;
import de.denis.mes.service.MaschineService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import de.denis.mes.dto.MaschineErstellenRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;

@Path("/maschinen")
public class MaschineResource {

    @Inject
    private MaschineService maschineService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Maschine> alleMaschinen() {
        return maschineService.alleFinden();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response maschineErstellen(MaschineErstellenRequest request) {

        Maschine maschine = maschineService.erstellen(
                request.getName(),
                request.getStatus()
        );

        return Response
                .status(Response.Status.CREATED)
                .entity(maschine)
                .build();
    }


}