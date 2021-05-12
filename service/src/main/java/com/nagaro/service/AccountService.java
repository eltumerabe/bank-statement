package com.nagaro.service;

import com.nagaro.common.model.ui.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAccounts();
}
