package com.plamena.novartoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class AccountStatus extends BaseModel {
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "accountStatus",
            cascade = CascadeType.REFRESH)
    private List<Account> accounts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
