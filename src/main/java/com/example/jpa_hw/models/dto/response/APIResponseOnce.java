package com.example.jpa_hw.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponseOnce<T>{
    String message;
    T payload;
    HttpStatus status;
    LocalDateTime time;
}
