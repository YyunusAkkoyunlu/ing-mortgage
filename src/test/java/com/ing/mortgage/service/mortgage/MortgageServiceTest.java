package com.ing.mortgage.service.mortgage;

import com.ing.mortgage.exception.MortgageRequestException;
import com.ing.mortgage.model.interest.InterestRate;
import com.ing.mortgage.model.interest.InterestRateReturn;
import com.ing.mortgage.model.mortgageCheck.MortgageCheck;
import com.ing.mortgage.model.mortgageCheck.MortgageCheckReturn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MortgageServiceTest {

    private MortgageService mortgageService;


    @BeforeEach
    void setUp() {
        // set up data.
        List<InterestRate> rates = new ArrayList<>();
        rates.add(new InterestRate(24, 2, new Date()));
        rates.add(new InterestRate(48, 4, new Date()));

        // prefered this way because annotations caused problems.
        InterestRateReturn interestRateReturn = new InterestRateReturn();
        interestRateReturn.setRates(rates);

        // prefered this way because annotations caused problems.
        mortgageService = new MortgageService();
        mortgageService.setInterestRateReturn(interestRateReturn);
    }

    /**
     * this test checks mortgageService.mortgageCheck working correct or not
     *
     * @throws Exception
     */
    @Test
    void mortgageCheck() throws Exception {
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

        // called mortgageService.mortgageCheck with data.
        MortgageCheckReturn mortgageCheckReturn = mortgageService.mortgageCheck(mortgageCheck);

        // check expected cost and returned data's cost
        assertEquals(mortgageCheckReturn.getCosts(), expectedMortgageCheckReturn.getCosts());
        // check expected feasible and returned data's feasible
        assertEquals(mortgageCheckReturn.isFeasible(), expectedMortgageCheckReturn.isFeasible());
    }

    /**
     * this test checks loan value can not greater that home value if block.
     *
     * @throws Exception
     */
    @Test
    void loanValueCanNotGreaterThanHomeValue() throws Exception {
        // set up expected data.
        MortgageCheckReturn expectedMortgageCheckReturn = new MortgageCheckReturn();
        expectedMortgageCheckReturn.setFeasible(true);
        expectedMortgageCheckReturn.setCosts(4250);

        // set up wrong data.
        MortgageCheck mortgageCheck = new MortgageCheck();
        mortgageCheck.setIncome(10000);
        mortgageCheck.setMaturityPeriod(24);
        mortgageCheck.setLoanValue(150000);
        mortgageCheck.setHomeValue(110000);

        // check service catch the exception.
        assertThrows(MortgageRequestException.class, () -> mortgageService.mortgageCheck(mortgageCheck));
    }

    /**
     * this test checks home value not exceed 4 times the income if block.
     *
     * @throws Exception
     */
    @Test
    void homeValueshouldNotExceed4TimesTheIncome() throws Exception {
        // set up expected data.
        MortgageCheckReturn expectedMortgageCheckReturn = new MortgageCheckReturn();
        expectedMortgageCheckReturn.setFeasible(true);
        expectedMortgageCheckReturn.setCosts(4250);

        // set up wrong data.
        MortgageCheck mortgageCheck = new MortgageCheck();
        mortgageCheck.setIncome(10000);
        mortgageCheck.setMaturityPeriod(24);
        mortgageCheck.setLoanValue(10000);
        mortgageCheck.setHomeValue(110000);

        // check service catch the exception.
        assertThrows(MortgageRequestException.class, () -> mortgageService.mortgageCheck(mortgageCheck));
    }

    /**
     * this test checks maturity period not found block
     *
     * @throws Exception
     */
    @Test
    void maturiyPeriodNotFound() throws Exception {
        // set up expected data.
        MortgageCheckReturn expectedMortgageCheckReturn = new MortgageCheckReturn();
        expectedMortgageCheckReturn.setFeasible(true);
        expectedMortgageCheckReturn.setCosts(4250);

        // set up wrong data.
        MortgageCheck mortgageCheck = new MortgageCheck();
        mortgageCheck.setIncome(10000);
        mortgageCheck.setMaturityPeriod(20);
        mortgageCheck.setLoanValue(100000);
        mortgageCheck.setHomeValue(110000);

        // check service catch the exception.
        assertThrows(MortgageRequestException.class, () -> mortgageService.mortgageCheck(mortgageCheck));
    }

}