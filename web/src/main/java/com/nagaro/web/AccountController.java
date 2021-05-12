package com.nagaro.web;

import com.nagaro.common.model.dto.AccountRest;
import com.nagaro.common.model.ui.AccountDto;
import com.nagaro.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public List<AccountRest> getAccounts(){
        List<AccountRest> accountRests = new ArrayList<AccountRest>();
        List<AccountDto> accounts = accountService.getAccounts();
        modelMapper.map(accounts,accountRests);
        return accountRests;
    }
}
