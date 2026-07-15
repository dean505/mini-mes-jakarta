package de.denis.mes.dto;

import de.denis.mes.entity.MaschinenStatus;

public class MaschineResponse {

    private Long id;
    private String name;
    private MaschinenStatus status;

    public MaschineResponse() {
    }

    public MaschineResponse(Long id,
                            String name,
                            MaschinenStatus status) {

        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(MaschinenStatus status) {
        this.status = status;
    }
}
