package com.plamena.novartoapi.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.plamena.novartoapi.serializer.AccountSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@Entity
@JsonSerialize(using = AccountSerializer.class)
public class Account extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "account_status_id")
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "account",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "account",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Payment> payments;

    @OneToMany(mappedBy = "account",
            cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Customer> customers;

    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp createDate;

    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp nextPaymentDate;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Timestamp getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(Timestamp nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
