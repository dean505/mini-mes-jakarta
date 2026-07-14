package de.denis.mes.service;

import de.denis.mes.entity.Maschine;
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
    public void speichern(Maschine maschine) {
        maschineRepository.speichern(maschine);
    }


    public List<Maschine> alleFinden() {
        return maschineRepository.alleFinden();
    }
}
