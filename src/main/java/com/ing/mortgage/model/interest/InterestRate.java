package com.ing.mortgage.model.interest;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterestRate {

    private int maturityPeriod;

    private float interestRate;

    private Date lastUpdate;

}
