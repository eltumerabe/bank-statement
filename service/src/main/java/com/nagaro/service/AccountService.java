package com.nagaro.service;

import com.nagaro.common.model.ui.AccountRest;

import java.util.List;

public interface AccountService {
    List<AccountRest> getAccounts();
}
