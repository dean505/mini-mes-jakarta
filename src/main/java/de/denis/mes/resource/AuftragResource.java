package de.denis.mes.resource;

import de.denis.mes.dto.AuftragErstellenRequest;
import de.denis.mes.dto.AuftragResponse;
import de.denis.mes.entity.AuftragsStatus;
import de.denis.mes.service.AuftragService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.DefaultValue;
import de.denis.mes.dto.AuftragPageResponse;

import java.util.List;

@Path("/auftraege")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuftragResource {

    @Inject
    private AuftragService auftragService;

    @POST
    public Response erstellen(
            @Valid AuftragErstellenRequest request
    ) {
        AuftragResponse response =
                auftragService.erstellen(request);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    public Response filtern(
            @QueryParam("status") AuftragsStatus status,
            @QueryParam("maschineId") Long maschineId,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sort") @DefaultValue("id") String sort,
            @QueryParam("direction") @DefaultValue("asc") String direction
    ) {

        AuftragPageResponse response =
                auftragService.filtern(
                        status,
                        maschineId,
                        page,
                        size,
                        sort,
                        direction
                );

        return Response
                .ok(response)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findeNachId(
            @PathParam("id") Long id
    ) {

        AuftragResponse response =
                auftragService.findeNachId(id);

        return Response
                .ok(response)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response aktualisieren(
            @PathParam("id") Long id,
            @Valid AuftragErstellenRequest request
    ) {

        AuftragResponse response =
                auftragService.aktualisieren(id, request);

        return Response
                .ok(response)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response loeschen(
            @PathParam("id") Long id
    ) {

        auftragService.loeschen(id);

        return Response
                .noContent()
                .build();
    }
}
