package com.plamena.novartoapi.service;

import com.plamena.novartoapi.Constants;
import com.plamena.novartoapi.ErrorEnum;
import com.plamena.novartoapi.entity.Payment;
import com.plamena.novartoapi.entity.PaymentStatus;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.repository.PaymentRepository;
import com.plamena.novartoapi.repository.PaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private PaymentStatusRepository paymentStatusRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentStatusRepository paymentStatusRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Override
    public Payment get(Integer id) throws CustomException {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (!payment.isPresent()) {
            throw new CustomException(ErrorEnum.PAYMENT_DOES_NOT_EXISTS);
        }

        return payment.get();
    }

    @Override
    public List<Payment> get() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment payPayment(Integer id) throws CustomException {
        Payment payment = get(id);
        if (payment.getPaymentStatus().getCode().equals(Constants.PAYMENT_STATUS_PAID)) {
            throw new CustomException(ErrorEnum.PAYMENT_ALREADY_PAID);
        }

        Optional<PaymentStatus> paidStatus = paymentStatusRepository.findByCode(Constants.PAYMENT_STATUS_PAID);
        payment.setPaymentStatus(paidStatus.get());

        return paymentRepository.save(payment);
    }
}
