package com.plamena.novartoapi.service;

import com.plamena.novartoapi.ErrorEnum;
import com.plamena.novartoapi.entity.Invoice;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice get(Integer id) throws CustomException {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (!invoice.isPresent()) {
            throw new CustomException(ErrorEnum.INVOICE_DOES_NOT_EXISTS);
        }

        return invoice.get();
    }

    @Override
    public List<Invoice> get() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice create(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
