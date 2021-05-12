package com.nagaro.service.impl;

import com.nagaro.common.model.entity.Account;
import com.nagaro.common.model.ui.AccountRest;
import com.nagaro.dataaccess.repository.AccountRepository;
import com.nagaro.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AccountRest> getAccounts() {
        List<AccountRest> accountRests = new ArrayList<>();
        Iterable<Account> all = accountRepository.findAll();
        Iterator<Account> iterator = all.iterator();
        while (iterator.hasNext()) {
            accountRests.add(modelMapper.map(iterator.next(), AccountRest.class));
        }
        return accountRests;
    }
}
