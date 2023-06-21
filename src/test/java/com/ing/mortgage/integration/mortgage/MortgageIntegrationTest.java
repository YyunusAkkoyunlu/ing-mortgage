package com.ing.mortgage.integration.mortgage;

import com.ing.mortgage.model.mortgageCheck.MortgageCheck;
import com.ing.mortgage.model.mortgageCheck.MortgageCheckReturn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MortgageIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void beforeSetup() {
        baseUrl = baseUrl + ":" + port + "/api/mortgage-check";
    }

    @Test
    void mortgageCheck() {
        // created object and set expected data
        MortgageCheckReturn expectedMortgageCheckReturn = new MortgageCheckReturn();
        expectedMortgageCheckReturn.setFeasible(true);
        expectedMortgageCheckReturn.setCosts(4250);

        // created object and set data.
        MortgageCheck mortgageCheck = new MortgageCheck();
        mortgageCheck.setIncome(10000);
        mortgageCheck.setMaturityPeriod(24);
        mortgageCheck.setLoanValue(100000);
        mortgageCheck.setHomeValue(110000);

        // service call
        MortgageCheckReturn mortgageCheckReturn = restTemplate.postForObject(baseUrl, mortgageCheck, MortgageCheckReturn.class);

        // check mortgageCheckReturn not null
        assertNotNull(mortgageCheckReturn);
        // check mortgageCheckReturn cost equals to expectedMortgageCheckReturn cost
        assertEquals(mortgageCheckReturn.getCosts(), expectedMortgageCheckReturn.getCosts());
        // check mortgageCheckReturn feasible equals to expectedMortgageCheckReturn feasible
        assertEquals(mortgageCheckReturn.isFeasible(), expectedMortgageCheckReturn.isFeasible());
    }

    @Test
    void mortgageCheckWithWrongData() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        // created object and set expected data
        MortgageCheckReturn expectedMortgageCheckReturn = new MortgageCheckReturn();
        expectedMortgageCheckReturn.setFeasible(true);
        expectedMortgageCheckReturn.setCosts(4250);

        // set up wrong data.
        MortgageCheck mortgageCheck = new MortgageCheck();
        mortgageCheck.setIncome(10000);
        mortgageCheck.setMaturityPeriod(24);
        mortgageCheck.setLoanValue(600000);
        mortgageCheck.setHomeValue(110000);

        HttpEntity<MortgageCheck> httpEntity = new HttpEntity<>(mortgageCheck);

        // service call
        ResponseEntity<MortgageCheck> responseEntity = testRestTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, MortgageCheck.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

}
