package com.plamena.novartoapi.service;

import com.plamena.novartoapi.entity.Subscription;
import com.plamena.novartoapi.exception.CustomException;

import java.util.List;

public interface SubscriptionService {
    Subscription get(Integer id) throws CustomException;

    List<Subscription> get();
}
