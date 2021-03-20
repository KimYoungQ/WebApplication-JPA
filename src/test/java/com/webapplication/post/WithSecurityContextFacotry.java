package com.webapplication.post;

import com.webapplication.account.Account;
import com.webapplication.account.AccountService;
import com.webapplication.account.JoinForm;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithSecurityContextFacotry implements WithSecurityContextFactory<WithMockCustomUser> {

    @Autowired
    AccountService accountService;

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser withMockCustomUser) {
        String name = withMockCustomUser.value();
        String password = "123123123";

        JoinForm joinForm = new JoinForm();
        joinForm.setName(name);
        joinForm.setPassword("12345678");
        Account account = accountService.saveNewAccount(joinForm);

        UserDetails principal = accountService.loadUserByUsername(name);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        authentication.setDetails(new Account(account.getName(), account.getPassword()));
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}
