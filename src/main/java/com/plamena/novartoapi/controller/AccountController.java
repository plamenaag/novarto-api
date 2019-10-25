package com.plamena.novartoapi.controller;

import com.plamena.novartoapi.dto.ServiceResponse;
import com.plamena.novartoapi.entity.Account;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@SuppressWarnings("Duplicates")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ServiceResponse get(@PathVariable("id") Integer id) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            response.setData(accountService.get(id));
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
            response.setData(accountService.get());
        } catch (Exception ex) {
            response.setStatusCode(ServiceResponse.Status.FAILURE);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping
    public ServiceResponse create(@RequestBody Account account) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            Account createdAccount = accountService.create(account);
            response.setData(createdAccount);
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

    @PutMapping("/{id}")
    public ServiceResponse update(@PathVariable() Integer id, @RequestBody Account account) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            Account updatedAccount = accountService.update(id, account);
            response.setData(updatedAccount);
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

    @DeleteMapping("/{id}")
    public ServiceResponse delete(@PathVariable() Integer id) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.Status.SUCCESS);
        try {
            accountService.delete(id);
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
