package com.ing.mortgage.controller.mortgage;

import com.ing.mortgage.model.mortgageCheck.MortgageCheck;
import com.ing.mortgage.model.mortgageCheck.MortgageCheckReturn;
import com.ing.mortgage.service.mortgage.MortgageService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class MortgageController {

    @Autowired
    MortgageService mortgageService;

    /**
     * This end point returns mortgageCheckReturn object according to the input data
     *
     * @param mortgageCheck
     * @return ResponseEntity<MortgageCheckReturn>
     * @throws Exception
     */
    @PostMapping("mortgage-check")
    public ResponseEntity<MortgageCheckReturn> mortgageCheck(@RequestBody @Valid MortgageCheck mortgageCheck) throws Exception {
        log.info("MortgageController_mortgageCheck");
        MortgageCheckReturn mortgageCheckReturn = mortgageService.mortgageCheck(mortgageCheck);
        log.info("mortgageCheckReturn: " + mortgageCheckReturn);

        return new ResponseEntity<>(mortgageCheckReturn, HttpStatus.OK);
    }

}
