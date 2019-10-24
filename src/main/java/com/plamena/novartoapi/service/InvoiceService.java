package com.plamena.novartoapi.service;

import com.plamena.novartoapi.entity.Invoice;
import com.plamena.novartoapi.exception.CustomException;

import java.util.List;

public interface InvoiceService {
    Invoice get(Integer id) throws CustomException;

    List<Invoice> get();

    Invoice create(Invoice invoice);
}
