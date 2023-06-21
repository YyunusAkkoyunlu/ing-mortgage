package com.ing.mortgage.integration.interest;

import com.ing.mortgage.model.interest.InterestRate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InterestIntegrationTest {

    @LocalServerPort
    // helps create port
    private int port;

    private static String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        // created RestTemplate object for real service call
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void beforeSetup() {
        // prepared url
        baseUrl = baseUrl + ":" + port + "/api/interest-rates";
    }

    @Test
    void shouldFetchAllInterestRates() {
        // service call and result assigned to the interestRateList list
        List<InterestRate> interestRateList = restTemplate.getForObject(baseUrl, List.class);

        // checked list size is correct or not
        assertThat(interestRateList.size()).isEqualTo(2);
    }

}
