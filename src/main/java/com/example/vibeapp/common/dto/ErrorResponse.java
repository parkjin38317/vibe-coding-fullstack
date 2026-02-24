package com.example.vibeapp.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Common Error Response")
public record ErrorResponse(
        @Schema(description = "Error message", example = "Invalid request parameters") String message,

        @Schema(description = "Specific error code or field name", example = "title") String detail) {
}
