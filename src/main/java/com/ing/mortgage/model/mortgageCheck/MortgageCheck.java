package com.ing.mortgage.model.mortgageCheck;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageCheck {

    private float income;

    private int maturityPeriod;

    private float loanValue;

    private float homeValue;

}
