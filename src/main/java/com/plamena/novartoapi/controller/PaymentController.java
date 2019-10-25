package com.plamena.novartoapi.controller;

import com.plamena.novartoapi.dto.ServiceResponse;
import com.plamena.novartoapi.entity.Invoice;
import com.plamena.novartoapi.entity.Payment;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.service.InvoiceService;
import com.plamena.novartoapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1/payments")
@SuppressWarnings("Duplicates")
public class PaymentController {
    private PaymentService paymentService;
    private InvoiceService invoiceService;

    @Autowired
    public PaymentController(PaymentService paymentService, InvoiceService invoiceService) {
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{id}")
    public ServiceResponse get(@PathVariable("id") Integer id) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            response.setData(paymentService.get(id));
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
            response.setData(paymentService.get());
        } catch (Exception ex) {
            response.setStatusCode(ServiceResponse.Status.FAILURE);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PutMapping("/{id}/pay")
    public ServiceResponse payPayment(@PathVariable() Integer id) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            Payment paidPayment = paymentService.payPayment(id);

            Invoice invoice = new Invoice();
            invoice.setAccount(paidPayment.getAccount());
            invoice.setPayment(paidPayment);
            invoice.setPaidAmount(paidPayment.getPrice());
            invoice.setDate(new Timestamp(System.currentTimeMillis()));

            invoiceService.create(invoice);

            response.setData(paidPayment);
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
}
