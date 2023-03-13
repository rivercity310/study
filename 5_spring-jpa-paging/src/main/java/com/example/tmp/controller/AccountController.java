package com.example.tmp.controller;

import com.example.tmp.dto.AccountDto;
import com.example.tmp.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountDto.Res signUp(@RequestBody @Valid final AccountDto.SignUpReq dto) {
        return new AccountDto.Res(accountService.create(dto));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDto.Res getUser(@PathVariable final Long id) {
        return new AccountDto.Res(accountService.findById(id));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDto.Res updateMyAccount(
            @PathVariable final Long id,
            @RequestBody final AccountDto.MyAccountReq dto
    ) {
        return new AccountDto.Res(accountService.updateMyAccount(id, dto));
    }
}
