package de.denis.mes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "maschinen")
public class Maschine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaschinenStatus status;

    public Maschine() {
    }

    public Maschine(String name, MaschinenStatus status) {
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MaschinenStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(MaschinenStatus status) {
        this.status = status;
    }
}
