package org.horizon.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTagRequest {
    @NotBlank
    private String name;
}
