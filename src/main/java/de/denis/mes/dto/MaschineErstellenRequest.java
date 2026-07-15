package de.denis.mes.dto;

import de.denis.mes.entity.MaschinenStatus;

public class MaschineErstellenRequest {

    private String name;
    private MaschinenStatus status;

    public MaschineErstellenRequest() {
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
