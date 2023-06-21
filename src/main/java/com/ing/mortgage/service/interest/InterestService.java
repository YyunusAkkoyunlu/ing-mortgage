package com.ing.mortgage.service.interest;

import com.ing.mortgage.model.interest.InterestRate;
import com.ing.mortgage.model.interest.InterestRateReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InterestService {

    /**
     * we can access interest rates. You can check InterestRateReturn class comment block to see logic.
     */
    @Autowired
    private InterestRateReturn interestRateReturn;


    /**
     * This method return all interest rates
     *
     * @return List<InterestRate>
     */
    public List<InterestRate> getAll() {
        log.info("InterestService_getAll");
        List<InterestRate> interestRateList = interestRateReturn.getRates();
        log.info("interestRateList: " + interestRateList);

        return interestRateList;
    }

}
