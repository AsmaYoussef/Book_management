package org.horizon.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTagRequest {
    @NotBlank
    private String name;
}
