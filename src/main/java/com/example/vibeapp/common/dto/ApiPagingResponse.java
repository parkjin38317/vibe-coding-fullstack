package com.example.vibeapp.common.dto;

import java.util.List;

public record ApiPagingResponse<T>(
        List<T> content,
        int totalPages,
        int currentPage,
        long totalElements) {
}
