package com.plamena.novartoapi.service;

import com.plamena.novartoapi.ErrorEnum;
import com.plamena.novartoapi.entity.Subscription;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription get(Integer id) throws CustomException {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (!subscription.isPresent()) {
            throw new CustomException(ErrorEnum.SUBSCRIPTION_DOES_NOT_EXISTS);
        }

        return subscription.get();
    }

    @Override
    public List<Subscription> get() {
        return subscriptionRepository.findAll();
    }
}
