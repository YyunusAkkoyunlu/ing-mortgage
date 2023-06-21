package com.ing.mortgage.service.interest;

import com.ing.mortgage.model.interest.InterestRate;
import com.ing.mortgage.model.interest.InterestRateReturn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InterestServiceTest {

    @InjectMocks
    private InterestService interestService;

    @Mock
    private InterestRateReturn interestRateReturn;


    /**
     * this test checks size of result is correct and not null.
     *
     * @throws Exception
     */
    @Test
    void getMortgageRates() {
        // create interest rate list for when clause
        List<InterestRate> interestRates = new ArrayList<>();
        interestRates.add(new InterestRate(24, 2, new Date()));
        interestRates.add(new InterestRate(48, 4, new Date()));

        // when interestService.getAll() service called, we expect interestRates list.
        when(interestService.getAll()).thenReturn(interestRates);

        // called interestService.getAll() and assigned to result list.
        List<InterestRate> result = interestService.getAll();

        // check size is correct
        assertEquals(2, result.size());
        // check result is not null
        assertNotNull(result);
    }

}