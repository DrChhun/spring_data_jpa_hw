package com.example.jpa_hw.models.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class GetResponse {
    public static ResponseEntity<?> responseAll(String message, Object data, int totalRecord) {
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

    public static ResponseEntity<?> responseDelete(String message) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponseDelete.builder()
                .mesage(message)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build());
    }

    public static ResponseEntity<?> responseList(String message, List<Object> payload) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponseList.builder()
                .message(message)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .payload(payload)
                .build());
    }
}
