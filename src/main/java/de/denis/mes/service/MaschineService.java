package de.denis.mes.service;

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
    public Maschine erstellen(String name, MaschinenStatus status) {
        Maschine maschine = new Maschine(name, status);
        maschineRepository.speichern(maschine);
        return maschine;
    }

    public List<Maschine> alleFinden() {
        return maschineRepository.alleFinden();
    }

    @Transactional
    public Maschine aktualisieren(Long id,
                                  String name,
                                  MaschinenStatus status) {

        Maschine maschine = maschineRepository.nachIdFinden(id);

        if (maschine == null) {
            return null;
        }

        maschine.setName(name);
        maschine.setStatus(status);

        return maschineRepository.aktualisieren(maschine);
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
}
