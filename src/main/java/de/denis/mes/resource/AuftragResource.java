package de.denis.mes.resource;

import de.denis.mes.dto.AuftragErstellenRequest;
import de.denis.mes.dto.AuftragResponse;
import de.denis.mes.service.AuftragService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
}
