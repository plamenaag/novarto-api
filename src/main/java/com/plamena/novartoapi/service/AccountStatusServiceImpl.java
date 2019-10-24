package com.plamena.novartoapi.service;

import com.plamena.novartoapi.ErrorEnum;
import com.plamena.novartoapi.entity.AccountStatus;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.repository.AccountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {
    private AccountStatusRepository accountStatusRepository;

    @Autowired
    public AccountStatusServiceImpl(AccountStatusRepository accountStatusRepository) {
        this.accountStatusRepository = accountStatusRepository;
    }

    @Override
    public AccountStatus get(Integer id) throws CustomException {
        Optional<AccountStatus> accountStatus = accountStatusRepository.findById(id);
        if (!accountStatus.isPresent()) {
            throw new CustomException(ErrorEnum.ACCOUNT_STATUS_DOES_NOT_EXIST);
        }
        return accountStatus.get();
    }

    @Override
    public List<AccountStatus> get() {
        return accountStatusRepository.findAll();
    }
}
