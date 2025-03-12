package org.czaraul.subscriptionapp.controller;

import org.czaraul.subscriptionapp.controller.mocked.MockedAWSSecretManager;
import org.czaraul.subscriptionapp.controller.mocked.MockedSDK;
import org.czaraul.subscriptionapp.controller.model.UserSubscriptionRequest;
import org.czaraul.subscriptionapp.controller.model.UserSubscriptionResponse;
import org.czaraul.subscriptionapp.service.UserSubscriptionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserSubscriptionControllerTest {

    private static UserSubscriptionService userSubscriptionService;

    private static MockedSDK mockSecretsManager;

    private static UserSubscriptionController userSubscriptionController;

    @BeforeAll
    public static void setUp() {
        userSubscriptionService = mock(UserSubscriptionService.class);
        mockSecretsManager = new MockedAWSSecretManager();

        userSubscriptionController = new UserSubscriptionController(userSubscriptionService, mockSecretsManager);
    }

    @Test
    public void testTokenRefresh_expiredToken() {
       userSubscriptionController.setTokenExpiration(LocalDate.now().minusDays(1));


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);

       UserSubscriptionRequest userSubscriptionRequest = UserSubscriptionRequest.builder()
               .id(1L)
               .userName("test-reference")
               .startDate(new Date())
               .endDate(calendar.getTime())
               .build();

        UserSubscriptionResponse userSubscriptionResponse = UserSubscriptionResponse.builder()
                .id(userSubscriptionRequest.getId())
                .startDate(new Date())
                .endDate(calendar.getTime()).build();
        when(userSubscriptionService.createUserSubscription(any(UserSubscriptionRequest.class))).thenReturn(userSubscriptionResponse);

        userSubscriptionController.createUserSubscription(userSubscriptionRequest);

        System.out.println("Expiration date is: " + userSubscriptionController.getTokenExpiration());
        assertTrue(userSubscriptionController.getTokenExpiration().isAfter(LocalDate.now()));
        assertTrue(userSubscriptionController.getTokenExpiration().isBefore(LocalDate.now().plus(1, ChronoUnit.WEEKS).plusDays(1)));


    }

    @Test
    public void testTokenRefresh_noToken() {
        userSubscriptionController.setInsuranceApiToken(null);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        UserSubscriptionRequest userSubscriptionRequest = UserSubscriptionRequest.builder()
                .userName("test-reference")
                .startDate(new Date())
                .endDate(calendar.getTime())
                .build();

        userSubscriptionController.createInsurancePolicy("test-reference", new Date(), calendar.getTime());

        assertNotNull(userSubscriptionController.getInsuranceApiToken());
        assertTrue(userSubscriptionController.getTokenExpiration().isAfter(LocalDate.now()));
        assertTrue(userSubscriptionController.getTokenExpiration().isBefore(LocalDate.now().plus(1, ChronoUnit.WEEKS).plusDays(1)));

    }
}
