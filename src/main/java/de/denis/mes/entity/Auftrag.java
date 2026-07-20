package de.denis.mes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "auftrag")
public class Auftrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bezeichnung;

    private Integer stueckzahl;

    @Enumerated(EnumType.STRING)
    private AuftragsStatus status;

    @ManyToOne
    @JoinColumn(name = "maschine_id")
    private Maschine maschine;

    public Auftrag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Integer getStueckzahl() {
        return stueckzahl;
    }

    public void setStueckzahl(Integer stueckzahl) {
        this.stueckzahl = stueckzahl;
    }

    public AuftragsStatus getStatus() {
        return status;
    }

    public void setStatus(AuftragsStatus status) {
        this.status = status;
    }

    public Maschine getMaschine() {
        return maschine;
    }

    public void setMaschine(Maschine maschine) {
        this.maschine = maschine;
    }
}
