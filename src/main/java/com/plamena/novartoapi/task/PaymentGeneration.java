package com.plamena.novartoapi.task;

import com.plamena.novartoapi.Constants;
import com.plamena.novartoapi.entity.Account;
import com.plamena.novartoapi.entity.Invoice;
import com.plamena.novartoapi.entity.Payment;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.service.AccountService;
import com.plamena.novartoapi.service.PaymentService;
import com.plamena.novartoapi.service.PaymentStatusService;
import com.plamena.novartoapi.util.TimeUtil;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentGeneration {
    private AccountService accountService;
    private PaymentStatusService paymentStatusService;
    private PaymentService paymentService;

    private final static Logger LOGGER;
    static {
        LOGGER = Logger.getLogger(PaymentGeneration.class.getName());
        LOGGER.setLevel(Level.SEVERE);
    }


    @Autowired
    public PaymentGeneration(AccountService accountService, PaymentStatusService paymentStatusService, PaymentService paymentService) {
        this.accountService = accountService;
        this.paymentStatusService = paymentStatusService;
        this.paymentService = paymentService;
    }

    @Scheduled(fixedRate = 5 * 1000)
    public void generatePayment(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<Account> accounts = accountService.getByNextPaymentDate(now);

        for (Account account : accounts) {
            try {
                Payment payment = new Payment();
                payment.setAccount(account);
                payment.setSubscription(account.getSubscription());
                payment.setPrice(account.getSubscription().getPrice());
                payment.setPaymentStatus(paymentStatusService.getByCode(Constants.PAYMENT_STATUS_DUE));
                payment.setDate(now);
                paymentService.create(payment);

                account.setNextPaymentDate(TimeUtil.addDays(account.getNextPaymentDate(), Constants.PAYMENT_PERIOD));
                accountService.updateNextInvoiceDate(account);
            }catch (CustomException ex){
                LOGGER.log(Level.SEVERE,ex.getErrorCode());
            }
        }
    }
}
