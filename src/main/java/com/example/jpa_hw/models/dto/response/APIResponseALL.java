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
public class APIResponseALL<T>{
    String message;
    Long totalRecords;
    T payload;
    HttpStatus status;
    LocalDateTime time;
}
