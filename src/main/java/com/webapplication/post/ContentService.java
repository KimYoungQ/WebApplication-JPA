package com.webapplication.post;

import com.webapplication.account.Account;
import com.webapplication.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final AccountRepository accountRepository;
    private final ContentRepository contentRepository;

    public void saveContent(Content content, String name) {
        Account currentAccount = accountRepository.findByName(name);
        content.setWriter(currentAccount);
        content.setDate(new Date());
        content.setModifiedDate(new Date());
        contentRepository.save(content);
    }

    public void update(Content content, Long content_id) {
        Optional<Content> selectContent = contentRepository.findById(content_id);
        selectContent.get().setModifiedDate(new Date());
        selectContent.get().setSubject(content.getSubject());
        selectContent.get().setText(content.getText());
        contentRepository.save(selectContent.get());
    }
}
