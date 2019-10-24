package com.plamena.novartoapi.service;

import com.plamena.novartoapi.entity.PaymentStatus;
import com.plamena.novartoapi.exception.CustomException;

import java.util.List;

public interface PaymentStatusService {
    PaymentStatus get(Integer id) throws CustomException;

    List<PaymentStatus> get();

    PaymentStatus getByCode(String code) throws CustomException;
}
