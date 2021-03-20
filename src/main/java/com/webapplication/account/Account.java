package com.webapplication.account;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String password;

    @Transient
    private boolean userLogin;

    public Account() {
        this.userLogin = false;
    }
}
