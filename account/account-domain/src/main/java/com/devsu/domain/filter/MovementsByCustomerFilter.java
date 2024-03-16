package com.devsu.domain.filter;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementsByCustomerFilter {

  private LocalDate startDate;

  private LocalDate endDate;

  private Integer customerId;

  private String customerIdentification;
}
