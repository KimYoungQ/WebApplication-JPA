package com.webapplication.post;

import com.webapplication.account.Account;
import com.webapplication.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostService {

    private final AccountRepository accountRepository;
    private final ContentRepository contentRepository;

    public void saveContent(Content content, String name) {
        Account currentAccount = accountRepository.findByName(name);
        content.setWriter(currentAccount);
        content.setDate(new Date());
        content.setModifiedDate(new Date());
        contentRepository.save(content);
    }
}
