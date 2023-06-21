package com.ing.mortgage.service.mortgage;

import com.ing.mortgage.exception.MortgageRequestException;
import com.ing.mortgage.model.interest.InterestRate;
import com.ing.mortgage.model.interest.InterestRateReturn;
import com.ing.mortgage.model.mortgageCheck.MortgageCheck;
import com.ing.mortgage.model.mortgageCheck.MortgageCheckReturn;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@Data
public class MortgageService {

    /**
     * we can access interest rates. You can check InterestRateReturn class comment block to see logic.
     */
    @Autowired
    private InterestRateReturn interestRateReturn;


    /**
     * method checks if feasible or not according to the user input
     *
     * @param mortgageCheck
     * @return MortgageCheckReturn
     * @throws Exception
     */
    public MortgageCheckReturn mortgageCheck(MortgageCheck mortgageCheck) throws Exception {
        log.info("MortgageService_mortgageCheck");
//        log.info("mortgageCheck: " + mortgageCheck);

        // access all interest rates
        List<InterestRate> rates = interestRateReturn.getRates();
        // find interest rate according to the mortgageCheck's maturity period
        InterestRate interestRate = rates.stream().filter(rate -> rate.getMaturityPeriod() == mortgageCheck.getMaturityPeriod()).findFirst().orElseThrow(() -> new MortgageRequestException("Invalid maturity period: " + mortgageCheck.getMaturityPeriod()));
        log.info("interestRate: " + interestRate);

        // check if loan value greater than home value
        if (mortgageCheck.getLoanValue() > mortgageCheck.getHomeValue()) {
            log.error("Loan Value cannot be greater than Home Value");
            throw new MortgageRequestException("Loan Value cannot be greater than Home Value");
        }

        // check if 4 times income greater than loan value
        if (mortgageCheck.getIncome() * 4 > mortgageCheck.getLoanValue()) {
            log.error("Home Value should not exceed 4 times the income");
            throw new MortgageRequestException("Home Value should not exceed 4 times the income");
        }
        // calculate cost
        float cost = calculateCost(mortgageCheck.getLoanValue(), interestRate.getInterestRate(), mortgageCheck.getMaturityPeriod());

        // if everything is fine, create MortgageCheckReturn object, set cost and feasible and return object
        MortgageCheckReturn mortgageCheckReturn = new MortgageCheckReturn();
        mortgageCheckReturn.setCosts(cost);
        mortgageCheckReturn.setFeasible(true);

        return mortgageCheckReturn;
    }

    /**
     * calculate cost
     *
     * @param loanValue
     * @param interestRate
     * @param maturityPeriod
     * @return float
     */
    private float calculateCost(float loanValue, float interestRate, int maturityPeriod) {
        float interestCost = loanValue * (interestRate / 100);
        float totalCost = (interestCost + loanValue) / maturityPeriod;

        return totalCost;
    }

}
