package de.denis.mes.repository;

import de.denis.mes.entity.Auftrag;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class AuftragRepository {

    @PersistenceContext(unitName = "MiniMesPU")
    private EntityManager entityManager;

    public void speichern(Auftrag auftrag) {
        entityManager.persist(auftrag);
    }

    public List<Auftrag> alleFinden() {
        return entityManager
                .createQuery(
                        "SELECT a FROM Auftrag a ORDER BY a.id",
                        Auftrag.class
                )
                .getResultList();
    }

    public Auftrag nachIdFinden(Long id) {
        return entityManager.find(Auftrag.class, id);
    }

    public void loeschen(Auftrag auftrag) {
        entityManager.remove(auftrag);
    }
}
