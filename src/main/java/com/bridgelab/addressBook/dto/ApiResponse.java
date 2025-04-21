package com.bridgelab.addressBook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;

}
