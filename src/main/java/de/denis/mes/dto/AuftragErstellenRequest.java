package de.denis.mes.dto;

import de.denis.mes.entity.AuftragsStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AuftragErstellenRequest {

    @NotBlank(message = "Die Bezeichnung darf nicht leer sein.")
    private String bezeichnung;

    @NotNull(message = "Die Stückzahl muss angegeben werden.")
    @Positive(message = "Die Stückzahl muss größer als 0 sein.")
    private Integer stueckzahl;

    @NotNull(message = "Der Status muss angegeben werden.")
    private AuftragsStatus status;

    @NotNull(message = "Die Maschinen-ID muss angegeben werden.")
    private Long maschineId;

    public AuftragErstellenRequest() {
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

    public Long getMaschineId() {
        return maschineId;
    }

    public void setMaschineId(Long maschineId) {
        this.maschineId = maschineId;
    }
}