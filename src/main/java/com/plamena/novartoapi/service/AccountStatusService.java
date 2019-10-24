package com.plamena.novartoapi.service;

import com.plamena.novartoapi.entity.AccountStatus;
import com.plamena.novartoapi.exception.CustomException;

import java.util.List;

public interface AccountStatusService {
    AccountStatus get(Integer id) throws CustomException;

    List<AccountStatus> get();
}
