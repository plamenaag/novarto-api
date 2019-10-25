package com.plamena.novartoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class PaymentStatus extends BaseModel {
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentStatus",
            cascade = CascadeType.REFRESH)
    private List<Payment> payments;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
