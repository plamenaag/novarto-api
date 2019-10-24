package com.plamena.novartoapi.service;

import com.plamena.novartoapi.entity.Payment;
import com.plamena.novartoapi.exception.CustomException;

import java.util.List;

public interface PaymentService {
    Payment get(Integer id) throws CustomException;

    List<Payment> get();

    Payment create(Payment payment);

    Payment payPayment(Integer id) throws CustomException;
}
