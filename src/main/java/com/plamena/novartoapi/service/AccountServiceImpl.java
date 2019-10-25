package com.plamena.novartoapi.service;

import com.plamena.novartoapi.Constants;
import com.plamena.novartoapi.ErrorEnum;
import com.plamena.novartoapi.entity.Account;
import com.plamena.novartoapi.entity.AccountStatus;
import com.plamena.novartoapi.entity.Subscription;
import com.plamena.novartoapi.exception.CustomException;
import com.plamena.novartoapi.repository.AccountRepository;
import com.plamena.novartoapi.repository.AccountStatusRepository;
import com.plamena.novartoapi.repository.SubscriptionRepository;
import com.plamena.novartoapi.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private SubscriptionRepository subscriptionRepository;
    private AccountStatusRepository accountStatusRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, SubscriptionRepository subscriptionRepository,
                              AccountStatusRepository accountStatusRepository) {
        this.accountRepository = accountRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.accountStatusRepository = accountStatusRepository;
    }

    @Override
    public Account get(Integer id) throws CustomException {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new CustomException(ErrorEnum.ACCOUNT_DOES_NOT_EXIST);
        }
        return account.get();
    }

    @Override
    public List<Account> getByNextPaymentDate(Timestamp date) {
        return accountRepository.findByNextPaymentDate(date);
    }

    @Override
    public List<Account> get() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(Account account) throws CustomException {
        if (account.getSubscription() == null || account.getSubscription().getId() == null) {
            throw new CustomException(ErrorEnum.SUBSCRIPTION_IS_INVALID);
        }
        Optional<Subscription> subscription = subscriptionRepository.findById(account.getSubscription().getId());
        if (!subscription.isPresent()) {
            throw new CustomException(ErrorEnum.SUBSCRIPTION_IS_INVALID);
        }
        account.setSubscription(subscription.get());

        Optional<AccountStatus> accountStatus = accountStatusRepository.findByCode(Constants.ACCOUNT_STATUS_ACTIVE);
        account.setAccountStatus(accountStatus.get());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        account.setCreateDate(now);
        account.setNextPaymentDate(TimeUtil.addDays(now, Constants.PAYMENT_PERIOD));
        return accountRepository.save(account);
    }

    @Override
    public Account update(Integer id, Account account) throws CustomException {
        Account entityToUpdate = get(id);

        if (account.getSubscription() != null && account.getSubscription().getId() != null
                && !entityToUpdate.getSubscription().getId().equals(account.getSubscription().getId())) {
            Optional<Subscription> subscription = subscriptionRepository.findById(account.getSubscription().getId());

            if (!subscription.isPresent()) {
                throw new CustomException(ErrorEnum.SUBSCRIPTION_IS_INVALID);
            }

            if (entityToUpdate.getSubscription().getPrice() > subscription.get().getPrice()) {
                throw new CustomException(ErrorEnum.CAN_NOT_DOWNGRADE_SUBSCRIPTION);
            }

            Timestamp now = new Timestamp(System.currentTimeMillis());
            if (!TimeUtil.datesAreEqual(entityToUpdate.getNextPaymentDate(), now) &&
                    !TimeUtil.datesAreEqual(TimeUtil.addDays(entityToUpdate.getNextPaymentDate(), -30), now)) {
                throw new CustomException(ErrorEnum.CAN_UPGRADE_SUBSCRIPTION_ONLY_ON_BILL_DATE);
            }

            entityToUpdate.setSubscription(subscription.get());
        }

        if (account.getAccountStatus() != null && account.getAccountStatus().getId() != null) {
            Optional<AccountStatus> status = accountStatusRepository.findById(account.getAccountStatus().getId());
            if (!status.isPresent()) {
                throw new CustomException(ErrorEnum.ACCOUNT_STATUS_IS_INVALID);
            }
            entityToUpdate.setAccountStatus(status.get());
        }

        return accountRepository.save(entityToUpdate);
    }

    @Override
    public Account updateNextInvoiceDate(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(Integer id) throws CustomException {
        Account account = get(id);
        accountRepository.delete(account);
    }
}
