package com.plamena.novartoapi.service;

import com.plamena.novartoapi.entity.Account;
import com.plamena.novartoapi.exception.CustomException;

import java.sql.Timestamp;
import java.util.List;

public interface AccountService {
    Account get(Integer id) throws CustomException;

    List<Account> getByNextPaymentDate(Timestamp date);

    List<Account> get();

    Account create(Account account) throws CustomException;

    Account update(Integer id,Account account) throws CustomException;

    Account updateNextInvoiceDate(Account account) throws CustomException;

    void delete(Integer id) throws CustomException;
}
