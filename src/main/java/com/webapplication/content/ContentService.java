package com.webapplication.content;

import com.webapplication.account.Account;
import com.webapplication.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final AccountRepository accountRepository;
    private final ContentRepository contentRepository;

    public Content saveContent(Content content, String name) {
        Account currentAccount = accountRepository.findByName(name);
        content.setWriter(currentAccount);
        content.setDate(new Date());
        content.setModifiedDate(new Date());
        Content selectedContent = contentRepository.save(content);
        return selectedContent;
    }

    public void update(Content content, Long content_id) {
        Optional<Content> selectContent = contentRepository.findById(content_id);
        selectContent.get().setModifiedDate(new Date());
        selectContent.get().setSubject(content.getSubject());
        selectContent.get().setText(content.getText());
        contentRepository.save(selectContent.get());
    }

    public Page<Content> getContentList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id");

        return  contentRepository.findAll(pageable);
    }

    public Paging setPaging(Page<Content> contentPage) {
        return new Paging(contentPage.getTotalPages(), contentPage.getNumber(), contentPage.getSize(), 10);
    }
}
