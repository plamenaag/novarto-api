package com.plamena.novartoapi.repository;

import com.plamena.novartoapi.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
