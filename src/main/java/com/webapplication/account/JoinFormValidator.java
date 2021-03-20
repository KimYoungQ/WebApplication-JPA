package com.webapplication.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinFormValidator implements Validator {

    private final AccountRepository accountRepository;

   @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(JoinForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        JoinForm joinForm = (JoinForm)o;
        if (accountRepository.existsByName(joinForm.getName()) == true) {
            errors.rejectValue("name", "invalid.name", "이미 존재하는 ID입니다.");
        }
    }
}
