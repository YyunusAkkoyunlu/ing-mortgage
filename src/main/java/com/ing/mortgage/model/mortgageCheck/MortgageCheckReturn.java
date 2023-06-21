package com.ing.mortgage.model.mortgageCheck;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageCheckReturn {

    private boolean feasible;

    private float costs;

}
