package com.plamena.novartoapi.service;

import com.plamena.novartoapi.entity.Customer;
import com.plamena.novartoapi.exception.CustomException;

import java.util.List;

public interface CustomerService {
    Customer get(Integer id) throws CustomException;

    List<Customer> get();

    Customer create(Customer customer) throws CustomException;

    Customer update(Integer id, Customer customer) throws CustomException;

    void delete(Integer id) throws CustomException;
}
