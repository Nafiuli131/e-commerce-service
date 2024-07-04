package com.example.e_commerce_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateRangeRequest {

    private LocalDate startDate;
    private LocalDate endDate;
}
