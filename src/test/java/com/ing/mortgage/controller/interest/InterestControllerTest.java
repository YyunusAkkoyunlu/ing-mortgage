package com.ing.mortgage.controller.interest;

import com.ing.mortgage.model.interest.InterestRate;
import com.ing.mortgage.service.interest.InterestService;
import com.ing.mortgage.service.mortgage.MortgageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class InterestControllerTest {

    @MockBean
    private InterestService interestService;

    @MockBean
    private MortgageService mortgageService;

    @Autowired
    private MockMvc mockMvc;


    /**
     * This test controls size of interest rates are correct or not
     *
     * @throws Exception
     */
    @Test
    public void shouldFetchAllInterestRates() throws Exception {
        // created a list for the retun
        List<InterestRate> rates = new ArrayList<>();
        rates.add(new InterestRate(24, 2, new Date()));
        rates.add(new InterestRate(48, 4, new Date()));

        // when getAll method call, return our rates list
        when(interestService.getAll()).thenReturn(rates);

        // perform and check the condition
        this.mockMvc.perform(get("/api/interest-rates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(rates.size())));
    }

}