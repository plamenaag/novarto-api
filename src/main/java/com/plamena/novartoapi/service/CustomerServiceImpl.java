package com.plamena.novartoapi.service;

import com.plamena.novartoapi.ErrorEnum;
import com.plamena.novartoapi.entity.Account;
import com.plamena.novartoapi.entity.Customer;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.repository.AccountRepository;
import com.plamena.novartoapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Customer get(Integer id) throws CustomException {
        Optional<Customer> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            throw new CustomException(ErrorEnum.CUSTOMER_DOES_NOT_EXIST);
        }

        return customer.get();
    }

    @Override
    public List<Customer> get() {
        return customerRepository.findAll();
    }

    @Override
    public Customer create(Customer customer) throws CustomException {
        if (customer.getUsername() == null) {
            throw new CustomException(ErrorEnum.USERNAME_IS_REQUIRED);
        }

        Optional<Customer> foundCustomer = customerRepository.findByUsername(customer.getUsername());
        if (foundCustomer.isPresent()) {
            throw new CustomException(ErrorEnum.USERNAME_ALREADY_EXISTS);
        }

        if (customer.getFirstName() == null) {
            throw new CustomException(ErrorEnum.FIRST_NAME_IS_REQUIRED);
        }

        if (customer.getLastName() == null) {
            throw new CustomException(ErrorEnum.LAST_NAME_IS_REQUIRED);
        }

        if (customer.getPassword() == null) {
            throw new CustomException(ErrorEnum.PASSWORD_IS_REQUIRED);
        }

        if (customer.getAccount() == null || customer.getAccount().getId() == null) {
            throw new CustomException(ErrorEnum.CUSTOMER_MUST_HAVE_AN_ACCOUNT);
        }

        Optional<Account> account = accountRepository.findById(customer.getAccount().getId());
        if (!account.isPresent()) {
            throw new CustomException(ErrorEnum.ACCOUNT_IS_INVALID);
        }
        customer.setAccount(account.get());

        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Integer id, Customer customer) throws CustomException {
        Customer entityToUpdate = get(id);

        if (customer.getUsername() == null) {
            throw new CustomException(ErrorEnum.USERNAME_IS_REQUIRED);
        }

        if (!customer.getUsername().equals(entityToUpdate.getUsername())) {
            Optional<Customer> foundCustomer = customerRepository.findByUsername(customer.getUsername());
            if (foundCustomer.isPresent()) {
                throw new CustomException(ErrorEnum.USERNAME_ALREADY_EXISTS);
            }
        }

        if (customer.getFirstName() != null) {
            throw new CustomException(ErrorEnum.FIRST_NAME_IS_REQUIRED);
        }

        if (customer.getLastName() != null) {
            throw new CustomException(ErrorEnum.LAST_NAME_IS_REQUIRED);
        }

        entityToUpdate.setFirstName(customer.getFirstName());
        entityToUpdate.setLastName(customer.getLastName());

        if (entityToUpdate.getPassword() != null) {
            entityToUpdate.setPassword(customer.getPassword());
        }

        return customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) throws CustomException {
        Customer customer = get(id);
        customerRepository.delete(customer);
    }
}
