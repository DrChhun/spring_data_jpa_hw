package com.example.jpa_hw.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class APIResponseList<T>{
    private String message;
    private List<T> payload;
    private LocalDateTime time;
    private HttpStatus status;
}
