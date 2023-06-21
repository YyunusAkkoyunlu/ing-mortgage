package com.ing.mortgage.controller.mortgage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.mortgage.exception.MortgageRequestException;
import com.ing.mortgage.model.mortgageCheck.MortgageCheck;
import com.ing.mortgage.model.mortgageCheck.MortgageCheckReturn;
import com.ing.mortgage.service.interest.InterestService;
import com.ing.mortgage.service.mortgage.MortgageService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MortgageControllerTest {

    @MockBean
    private MortgageService mortgageService;

    @MockBean
    InterestService interestService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private MortgageCheckReturn mortgageCheckReturn;

    @BeforeEach
    void setup() {
        mortgageCheckReturn = new MortgageCheckReturn();
        mortgageCheckReturn.setCosts(4250);
        mortgageCheckReturn.setFeasible(true);
    }


    /**
     * this test checks if end point get correct data and response is correct.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnMortgageCheck() throws Exception {
        // when mortgageService.mortgageCheck get any MortgageCheck class, should return mortgageCheckReturn on setup method.
        when(mortgageService.mortgageCheck(any(MortgageCheck.class))).thenReturn(mortgageCheckReturn);

        // after perform, check returned data from controller is match mortgageCheckReturn data.
        this.mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mortgageCheckReturn)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feasible", CoreMatchers.is(mortgageCheckReturn.isFeasible())))
                .andExpect(jsonPath("$.costs", CoreMatchers.is(mortgageCheckReturn.getCosts()), Float.class));
    }

    /**
     * this test checks if end point get wrong data and response is correct.
     *
     * @throws Exception
     */
    @Test
    public void notFeasible() throws Exception {
        // create data for mortgageService.mortgageCheck method.
        MortgageCheck mortgageCheck = new MortgageCheck();
        mortgageCheck.setIncome(10000);
        mortgageCheck.setMaturityPeriod(24);
        mortgageCheck.setLoanValue(600000);
        mortgageCheck.setHomeValue(110000);

        // when we got wrong data, expect an exception.
        when(mortgageService.mortgageCheck(mortgageCheck)).thenThrow(new MortgageRequestException("Loan Value cannot be greater than Home Value"));

        // after perform, check exception message is correct or not.
        this.mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mortgageCheck)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", CoreMatchers.is("Loan Value cannot be greater than Home Value")));
    }

}