package org.czaraul.subscriptionapp.model.data;

import org.czaraul.subscriptionapp.model.entity.UserSubscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {
}
