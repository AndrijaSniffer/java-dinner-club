package com.ates.dinnerClub.classes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Error response DTO")
public class ErrorResponse {
    @Schema(
            description = "Error type",
            example = "IllegalArgumentException"
    )
    private String error;

    @Schema(
            description = "Error message",
            example = "Invalid input"
    )
    private String message;
}
