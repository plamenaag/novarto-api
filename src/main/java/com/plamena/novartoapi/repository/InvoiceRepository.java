package com.plamena.novartoapi.repository;

import com.plamena.novartoapi.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
