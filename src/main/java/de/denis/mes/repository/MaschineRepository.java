package de.denis.mes.repository;

import de.denis.mes.entity.Maschine;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class MaschineRepository {

    @PersistenceContext(unitName = "MiniMesPU")
    private EntityManager entityManager;

    public void speichern(Maschine maschine) {
        entityManager.persist(maschine);
    }


    public List<Maschine> alleFinden() {
        return entityManager
                .createQuery("SELECT m FROM Maschine m ORDER BY m.id", Maschine.class)
                .getResultList();
    }
}
