package com.plamena.novartoapi.controller;

import com.plamena.novartoapi.dto.ServiceResponse;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
@SuppressWarnings("Duplicates")
public class SubscriptionController {
    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{id}")
    public ServiceResponse get(@PathVariable("id") Integer id) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            response.setData(subscriptionService.get(id));
        } catch (CustomException ex) {
            response.setStatusCode(ServiceResponse.Status.FAILURE);
            response.setErrorCode(ex.getErrorCode());
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setStatusCode(ServiceResponse.Status.FAILURE);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    @GetMapping
    public ServiceResponse getAll() {

        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            response.setData(subscriptionService.get());
        } catch (Exception ex) {
            response.setStatusCode(ServiceResponse.Status.FAILURE);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
}
