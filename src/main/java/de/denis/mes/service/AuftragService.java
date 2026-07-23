package de.denis.mes.service;

import de.denis.mes.dto.AuftragErstellenRequest;
import de.denis.mes.dto.AuftragResponse;
import de.denis.mes.entity.Auftrag;
import de.denis.mes.entity.AuftragsStatus;
import de.denis.mes.entity.Maschine;
import de.denis.mes.exception.ResourceNotFoundException;
import de.denis.mes.repository.AuftragRepository;
import de.denis.mes.repository.MaschineRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import de.denis.mes.dto.AuftragPageResponse;

import java.util.List;

@ApplicationScoped
public class AuftragService {

    @Inject
    private AuftragRepository auftragRepository;

    @Inject
    private MaschineRepository maschineRepository;

    @Transactional
    public AuftragResponse erstellen(AuftragErstellenRequest request) {

        Maschine maschine =
                maschineRepository.nachIdFinden(
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

        auftragRepository.speichern(auftrag);

        return erstelleResponse(auftrag);
    }

    public AuftragResponse findeNachId(Long id) {

        Auftrag auftrag =
                auftragRepository.nachIdFinden(id);

        if (auftrag == null) {
            throw new ResourceNotFoundException(
                    Auftrag.class.getSimpleName(),
                    id
            );
        }

        return erstelleResponse(auftrag);
    }

    @Transactional
    public void loeschen(Long id) {

        Auftrag auftrag =
                auftragRepository.nachIdFinden(id);

        if (auftrag == null) {
            throw new ResourceNotFoundException(
                    Auftrag.class.getSimpleName(),
                    id
            );
        }

        auftragRepository.loeschen(auftrag);
    }

    @Transactional
    public AuftragResponse aktualisieren(
            Long id,
            AuftragErstellenRequest request
    ) {

        Auftrag auftrag =
                auftragRepository.nachIdFinden(id);

        if (auftrag == null) {
            throw new ResourceNotFoundException(
                    Auftrag.class.getSimpleName(),
                    id
            );
        }

        Maschine maschine =
                maschineRepository.nachIdFinden(
                        request.getMaschineId()
                );

        if (maschine == null) {
            throw new ResourceNotFoundException(
                    Maschine.class.getSimpleName(),
                    request.getMaschineId()
            );
        }

        auftrag.setBezeichnung(request.getBezeichnung());
        auftrag.setStueckzahl(request.getStueckzahl());
        auftrag.setStatus(request.getStatus());
        auftrag.setMaschine(maschine);

        return erstelleResponse(auftrag);
    }

    public AuftragPageResponse filtern(
            AuftragsStatus status,
            Long maschineId,
            int page,
            int size,
            String sort,
            String direction
    ) {

        if (page < 0) {
            throw new IllegalArgumentException(
                    "Page darf nicht negativ sein"
            );
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Size muss zwischen 1 und 100 liegen"
            );
        }

        List<String> erlaubteSortierfelder = List.of(
                "id",
                "bezeichnung",
                "stueckzahl",
                "status"
        );

        if (!erlaubteSortierfelder.contains(sort)) {
            throw new IllegalArgumentException(
                    "Ungueltiges Sortierfeld"
            );
        }

        if (!direction.equalsIgnoreCase("asc")
                && !direction.equalsIgnoreCase("desc")) {

            throw new IllegalArgumentException(
                    "Sortierrichtung muss asc oder desc sein"
            );
        }

        String sortDirection = direction.toUpperCase();

        List<Auftrag> auftraege =
                auftragRepository.filtern(
                        status,
                        maschineId,
                        page,
                        size,
                        sort,
                        sortDirection
                );

        List<AuftragResponse> content =
                auftraege.stream()
                        .map(this::erstelleResponse)
                        .toList();

        long totalElements =
                auftragRepository.zaehlen(
                        status,
                        maschineId
                );

        int totalPages =
                (int) Math.ceil(
                        (double) totalElements / size
                );

        return new AuftragPageResponse(
                content,
                page,
                size,
                totalElements,
                totalPages
        );
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
