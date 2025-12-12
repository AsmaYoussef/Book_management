package org.horizon.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private Map<String, String> errors;
}
