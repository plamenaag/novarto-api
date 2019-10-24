package com.plamena.novartoapi.controller;

import com.plamena.novartoapi.dto.ServiceResponse;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.service.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paymentStatuses")
@SuppressWarnings("Duplicates")
public class PaymentStatusController {
    private PaymentStatusService paymentStatusService;

    @Autowired
    public PaymentStatusController(PaymentStatusService paymentStatusService) {
        this.paymentStatusService = paymentStatusService;
    }

    @GetMapping("/{id}")
    public ServiceResponse get(@PathVariable("id") Integer id) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            response.setData(paymentStatusService.get(id));
        }catch (CustomException ex){
            response.setStatusCode(ServiceResponse.Status.FAILURE);
            response.setErrorCode(ex.getErrorCode());
            response.setMessage(ex.getMessage());
        }catch (Exception ex){
            response.setStatusCode(ServiceResponse.Status.FAILURE);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping
    public ServiceResponse getAll() {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try{
            response.setData(paymentStatusService.get());

        }catch (Exception ex){
            response.setStatusCode(ServiceResponse.Status.FAILURE);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
}
