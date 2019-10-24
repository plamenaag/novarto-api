package com.plamena.novartoapi.repository;

import com.plamena.novartoapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("all")
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "Select * from account as acc " +
            " where date(acc.next_payment_date) = date(:date)", nativeQuery = true)
    List<Account> findByNextPaymentDate(@Param("date") Timestamp date);
}
