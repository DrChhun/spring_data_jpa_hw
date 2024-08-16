package com.example.jpa_hw.models.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class GetResponse {
    public static ResponseEntity<?> responseAll(String message, Object data, Long totalRecord) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponseALL.builder()
                .totalRecords(totalRecord)
                .message(message)
                .payload(data)
                .time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .build());
    }

    public static ResponseEntity<?> responseOnce(String message, Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponseOnce.builder()
                .message(message)
                .payload(data)
                .time(LocalDateTime.now())
                .status(HttpStatus.OK)
                .build());
    }

    public static ResponseEntity<?> responseCreate(String message, Object data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponseOnce.builder()
                .message(message)
                .payload(data)
                .time(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .build());
    }
}
