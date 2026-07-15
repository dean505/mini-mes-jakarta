package de.denis.mes.service;

import de.denis.mes.dto.MaschineResponse;
import de.denis.mes.entity.Maschine;
import de.denis.mes.entity.MaschinenStatus;
import de.denis.mes.repository.MaschineRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class MaschineService {

    @Inject
    private MaschineRepository maschineRepository;

    @Transactional
    public MaschineResponse erstellen(String name, MaschinenStatus status) {
        Maschine maschine = new Maschine(name, status);
        maschineRepository.speichern(maschine);

        return toResponse(maschine);
    }

    public List<MaschineResponse> alleFinden() {
        return maschineRepository
                .alleFinden()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public MaschineResponse aktualisieren(
            Long id,
            String name,
            MaschinenStatus status) {

        Maschine maschine = maschineRepository.nachIdFinden(id);

        if (maschine == null) {
            return null;
        }

        maschine.setName(name);
        maschine.setStatus(status);

        Maschine aktualisierteMaschine =
                maschineRepository.aktualisieren(maschine);

        return toResponse(aktualisierteMaschine);
    }

    @Transactional
    public boolean loeschen(Long id) {

        Maschine maschine = maschineRepository.nachIdFinden(id);

        if (maschine == null) {
            return false;
        }

        maschineRepository.loeschen(maschine);

        return true;
    }

    private MaschineResponse toResponse(Maschine maschine) {

        return new MaschineResponse(
                maschine.getId(),
                maschine.getName(),
                maschine.getStatus()
        );
    }

    public MaschineResponse nachIdFinden(Long id) {

        Maschine maschine = maschineRepository.nachIdFinden(id);

        if (maschine == null) {
            return null;
        }

        return toResponse(maschine);
    }
}
