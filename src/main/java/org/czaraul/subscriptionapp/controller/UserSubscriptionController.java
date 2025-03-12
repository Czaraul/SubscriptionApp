package org.czaraul.subscriptionapp.controller;

import lombok.extern.java.Log;
import org.czaraul.subscriptionapp.controller.mocked.MockedSDK;
import org.czaraul.subscriptionapp.controller.model.UserSubscriptionRequest;
import org.czaraul.subscriptionapp.controller.model.UserSubscriptionResponse;
import org.czaraul.subscriptionapp.service.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;

@RestController
@RequestMapping("/subscriptions")
@Log
public class UserSubscriptionController {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @Autowired
    private MockedSDK mockSecretsManager;

    private String insuranceApiToken;
    private LocalDate tokenExpiration;


    public UserSubscriptionController(UserSubscriptionService userSubscriptionService, MockedSDK mockSecretsManager) {
        this.userSubscriptionService = userSubscriptionService;
        this.mockSecretsManager = mockSecretsManager;
    }

    @PostMapping
    public ResponseEntity<UserSubscriptionResponse> createUserSubscription(@RequestBody UserSubscriptionRequest userSubscription) {
        try {
            UserSubscriptionResponse createdSubscription = userSubscriptionService.createUserSubscription(userSubscription);

            String policyReference = UUID.randomUUID().toString();

            createInsurancePolicy(policyReference, createdSubscription.getStartDate(), createdSubscription.getEndDate());

            return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);

        } catch (Exception e) {
            log.log(Level.INFO, "Error when creating User Subscription:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public void createInsurancePolicy(String policyReference, Date startDate, Date endDate) {

        if (insuranceApiToken == null || tokenExpiration == null || tokenExpiration.isBefore(LocalDate.now())) {
            insuranceApiToken = getInsuranceApiTokenFromSecrets();
            this.setTokenExpiration(LocalDate.now().plusWeeks(1));

            System.out.println("After expiration set: " + this.getTokenExpiration());
        }

        // Mock the API call to insurance.com (replace with actual API call if/when available)
        System.out.println("Calling insurance.com/policy with:");
        System.out.println("Token: " + insuranceApiToken);
        System.out.println("policy_reference: " + policyReference);
        System.out.println("start_date: " + startDate);
        System.out.println("end_date: " + endDate);

    }

    private String getInsuranceApiTokenFromSecrets() {
        return mockSecretsManager.getSecretValue();
    }

    public LocalDate getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(LocalDate tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public String getInsuranceApiToken() {
        return insuranceApiToken;
    }

    public void setInsuranceApiToken(String insuranceApiToken) {
        this.insuranceApiToken = insuranceApiToken;
    }
}
