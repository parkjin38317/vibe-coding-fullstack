package com.example.vibeapp.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Generic paginated API response")
public record ApiPagingResponse<T>(
                @Schema(description = "List of content items") List<T> content,

                @Schema(description = "Total number of pages", example = "10") int totalPages,

                @Schema(description = "Current page number (1-indexed)", example = "1") int currentPage,

                @Schema(description = "Total number of elements in the entire list", example = "50") long totalElements) {
}
