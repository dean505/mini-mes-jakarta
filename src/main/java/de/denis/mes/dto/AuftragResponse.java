package de.denis.mes.dto;

import de.denis.mes.entity.AuftragsStatus;

public class AuftragResponse {

    private Long id;
    private String bezeichnung;
    private Integer stueckzahl;
    private AuftragsStatus status;
    private Long maschineId;
    private String maschineName;

    public AuftragResponse() {
    }

    public AuftragResponse(
            Long id,
            String bezeichnung,
            Integer stueckzahl,
            AuftragsStatus status,
            Long maschineId,
            String maschineName
    ) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.stueckzahl = stueckzahl;
        this.status = status;
        this.maschineId = maschineId;
        this.maschineName = maschineName;
    }

    public Long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Integer getStueckzahl() {
        return stueckzahl;
    }

    public AuftragsStatus getStatus() {
        return status;
    }

    public Long getMaschineId() {
        return maschineId;
    }

    public String getMaschineName() {
        return maschineName;
    }
}
