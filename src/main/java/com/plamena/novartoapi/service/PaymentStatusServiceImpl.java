package com.plamena.novartoapi.service;

import com.plamena.novartoapi.ErrorEnum;
import com.plamena.novartoapi.entity.PaymentStatus;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.repository.PaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {
    private PaymentStatusRepository paymentStatusRepository;

    @Autowired
    public PaymentStatusServiceImpl(PaymentStatusRepository paymentStatusRepository) {
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Override
    public PaymentStatus get(Integer id) throws CustomException {
        Optional<PaymentStatus> paymentStatus = paymentStatusRepository.findById(id);
        if (!paymentStatus.isPresent()) {
            throw new CustomException(ErrorEnum.PAYMENT_STATUS_DOES_NOT_EXISTS);
        }

        return paymentStatus.get();
    }

    @Override
    public List<PaymentStatus> get() {
        return paymentStatusRepository.findAll();
    }

    @Override
    public PaymentStatus getByCode(String code) throws CustomException {
        Optional<PaymentStatus> paymentStatus = paymentStatusRepository.findByCode(code);
        if (!paymentStatus.isPresent()) {
            throw new CustomException(ErrorEnum.PAYMENT_STATUS_DOES_NOT_EXISTS);
        }

        return paymentStatus.get();
    }
}
