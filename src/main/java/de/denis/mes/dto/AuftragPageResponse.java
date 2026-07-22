package de.denis.mes.dto;

import java.util.List;

public class AuftragPageResponse {

    private List<AuftragResponse> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public AuftragPageResponse(
            List<AuftragResponse> content,
            int page,
            int size,
            long totalElements,
            int totalPages
    ) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<AuftragResponse> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
