package com.ing.mortgage.model.interest;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class maps InterestRate list to interest rate list in application.yml file. " mortgage " - @ConfigurationProperties("mortgage") - should match " mortgage " in application.yml
 * Also " rates " should match " rates "in application.yml.
 *
 * Used @Component annotation because we can use this component from everywhere with @Autowired annotation.
 *
 * Logic is, read and map interest rate list from application.yml file to the " rates " list on application startup. If we want to add/modify interest rates, we can do it in application.yml file.
 *
 */
@Component
@ConfigurationProperties("mortgage")
@Getter
@Setter
public class InterestRateReturn {

    private List<InterestRate> rates;

}
