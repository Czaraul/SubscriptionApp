package org.czaraul.subscriptionapp.service;

import org.czaraul.subscriptionapp.controller.model.UserSubscriptionRequest;
import org.czaraul.subscriptionapp.controller.model.UserSubscriptionResponse;

public interface UserSubscriptionService {

    UserSubscriptionResponse createUserSubscription(UserSubscriptionRequest userSubscription);

}
