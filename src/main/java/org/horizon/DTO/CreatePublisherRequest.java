package org.horizon.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePublisherRequest {
    @NotBlank
    private String name;
}
