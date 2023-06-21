package com.ing.mortgage.controller.interest;

import com.ing.mortgage.model.interest.InterestRate;
import com.ing.mortgage.service.interest.InterestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class InterestController {

    @Autowired
    private InterestService interestService;


    /**
     * This end point returns all interest rates
     */
    @GetMapping("/interest-rates")
    public ResponseEntity<List<InterestRate>> interestRates() {
        log.info("InterestController_interestRates");
        List<InterestRate> interestRates = interestService.getAll();
        log.info("interestRates: " + interestRates);

        return new ResponseEntity<>(interestRates, HttpStatus.OK);
    }

}
