package de.denis.mes.resource;

import de.denis.mes.entity.Maschine;
import de.denis.mes.service.MaschineService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import de.denis.mes.dto.MaschineErstellenRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.DELETE;
import de.denis.mes.dto.MaschineResponse;

@Path("/maschinen")
public class MaschineResource {

    @Inject
    private MaschineService maschineService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MaschineResponse> alleMaschinen() {
        return maschineService.alleFinden();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response maschineErstellen( @Valid MaschineErstellenRequest request) {

        MaschineResponse maschine = maschineService.erstellen(
                request.getName(),
                request.getStatus()
        );

        return Response
                .status(Response.Status.CREATED)
                .entity(maschine)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response maschineAktualisieren(
            @PathParam("id") Long id,
            @Valid MaschineErstellenRequest request) {

        MaschineResponse response = maschineService.aktualisieren(
                id,
                request.getName(),
                request.getStatus()
        );

        if (response == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response maschineNachId(@PathParam("id") Long id) {

        MaschineResponse response =
                maschineService.nachIdFinden(id);

        if (response == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response maschineLoeschen(@PathParam("id") Long id) {

        boolean geloescht = maschineService.loeschen(id);

        if (!geloescht) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }


}