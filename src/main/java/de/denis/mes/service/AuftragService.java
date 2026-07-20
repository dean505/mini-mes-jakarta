package de.denis.mes.service;

import de.denis.mes.dto.AuftragErstellenRequest;
import de.denis.mes.dto.AuftragResponse;
import de.denis.mes.entity.Auftrag;
import de.denis.mes.entity.Maschine;
import de.denis.mes.exception.ResourceNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AuftragService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public AuftragResponse erstellen(AuftragErstellenRequest request) {

        Maschine maschine = entityManager.find(
                Maschine.class,
                request.getMaschineId()
        );

        if (maschine == null) {
            throw new ResourceNotFoundException(
                    Maschine.class.getSimpleName(),
                    request.getMaschineId()
            );
        }

        Auftrag auftrag = new Auftrag();

        auftrag.setBezeichnung(request.getBezeichnung());
        auftrag.setStueckzahl(request.getStueckzahl());
        auftrag.setStatus(request.getStatus());
        auftrag.setMaschine(maschine);

        entityManager.persist(auftrag);

        return erstelleResponse(auftrag);
    }

    private AuftragResponse erstelleResponse(Auftrag auftrag) {

        return new AuftragResponse(
                auftrag.getId(),
                auftrag.getBezeichnung(),
                auftrag.getStueckzahl(),
                auftrag.getStatus(),
                auftrag.getMaschine().getId(),
                auftrag.getMaschine().getName()
        );
    }
}
