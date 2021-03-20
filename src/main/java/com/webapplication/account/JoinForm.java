package com.webapplication.account;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class JoinForm {

    @Length(min = 1, max = 20)
    @Pattern(regexp = "^[가-힣a-zA-Z]{1,20}$" ,message = "1~20자 영어 대 소문자, 한글만 가능합니다.")
    @NotBlank
    private String name;

    @Length(min = 8, max = 16)
    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "8~16자 영어 대 소문자, 숫자만 가능합니다.")
    @NotBlank
    private String password;
}
