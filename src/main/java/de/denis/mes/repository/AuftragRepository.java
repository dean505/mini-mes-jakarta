package de.denis.mes.repository;

import de.denis.mes.entity.Auftrag;
import de.denis.mes.entity.AuftragsStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class AuftragRepository {

    @PersistenceContext(unitName = "MiniMesPU")
    private EntityManager entityManager;

    public void speichern(Auftrag auftrag) {
        entityManager.persist(auftrag);
    }

    public Auftrag nachIdFinden(Long id) {
        return entityManager.find(Auftrag.class, id);
    }

    public List<Auftrag> filtern(
            AuftragsStatus status,
            Long maschineId,
            int page,
            int size
    ) {

        StringBuilder jpql = new StringBuilder(
                "SELECT a FROM Auftrag a WHERE 1 = 1"
        );

        if (status != null) {
            jpql.append(" AND a.status = :status");
        }

        if (maschineId != null) {
            jpql.append(" AND a.maschine.id = :maschineId");
        }

        jpql.append(" ORDER BY a.id");

        TypedQuery<Auftrag> query = entityManager.createQuery(
                jpql.toString(),
                Auftrag.class
        );

        if (status != null) {
            query.setParameter("status", status);
        }

        if (maschineId != null) {
            query.setParameter("maschineId", maschineId);
        }

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    public long zaehlen(
            AuftragsStatus status,
            Long maschineId
    ) {

        StringBuilder jpql = new StringBuilder(
                "SELECT COUNT(a) FROM Auftrag a WHERE 1 = 1"
        );

        if (status != null) {
            jpql.append(" AND a.status = :status");
        }

        if (maschineId != null) {
            jpql.append(" AND a.maschine.id = :maschineId");
        }

        TypedQuery<Long> query = entityManager.createQuery(
                jpql.toString(),
                Long.class
        );

        if (status != null) {
            query.setParameter("status", status);
        }

        if (maschineId != null) {
            query.setParameter("maschineId", maschineId);
        }

        return query.getSingleResult();
    }

    public void loeschen(Auftrag auftrag) {
        entityManager.remove(auftrag);
    }
}