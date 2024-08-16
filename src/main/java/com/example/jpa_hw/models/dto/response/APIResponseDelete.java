package com.example.jpa_hw.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDelete {
    private String mesage;
    private LocalDateTime time;
    private HttpStatus status;
}
