package org.czaraul.subscriptionapp.service;

import jakarta.transaction.Transactional;
import org.czaraul.subscriptionapp.controller.model.UserSubscriptionRequest;
import org.czaraul.subscriptionapp.controller.model.UserSubscriptionResponse;
import org.czaraul.subscriptionapp.model.data.UserSubscriptionRepository;
import org.czaraul.subscriptionapp.model.entity.UserSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    @Autowired
    private final UserSubscriptionRepository userSubscriptionRepository;

    public UserSubscriptionServiceImpl(UserSubscriptionRepository userSubscriptionRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
    }

    @Override
    @Transactional
    public UserSubscriptionResponse createUserSubscription(UserSubscriptionRequest userSubscription) {
        UserSubscription userSubscriptionEntity = UserSubscription
                .builder()
                .userId(userSubscription.getId())
                .startDate(userSubscription.getStartDate())
                .endDate(userSubscription.getEndDate())
                .autorenewEnabled(true)
                .build();

        UserSubscription persistedEntity = userSubscriptionRepository.save(userSubscriptionEntity);

        return UserSubscriptionResponse
                .builder()
                .id(persistedEntity.getId())
                .startDate(persistedEntity.getStartDate())
                .endDate(persistedEntity.getEndDate())
                .build();
    }
}
