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

@Path("/maschine")
public class MaschineResource {

    @Inject
    private MaschineService maschineService;

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public String createMaschine() {

        Maschine maschine =
                new Maschine("Laser 1", MaschinenStatus.LAEUFT);

        maschineService.speichern(maschine);

        return "Maschine gespeichert!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Maschine> alleMaschinen() {
        return maschineService.alleFinden();
    }
}
