package com.webapplication.post;

import com.webapplication.account.Account;
import com.webapplication.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final ContentRepository contentRepository;
    private final AccountRepository accountRepository;
    private final ContentService contentService;

    @GetMapping("/read")
    public String read(Model model, @RequestParam Long content_id,
                       Principal principal) {
        Optional<Content> selectedContent = contentRepository.findById(content_id);
        model.addAttribute("content", selectedContent.get());

        Long writerId = selectedContent.get().getWriter().getId();
        boolean matchResult = matchCurrentUserAndWriterUser(writerId, principal);
        model.addAttribute("matchResult", matchResult);
        return "post/read";
    }

    private boolean matchCurrentUserAndWriterUser(Long writerId, Principal principal) {
        Account selectedAccount = accountRepository.findByName(principal.getName());
        Long accountId = selectedAccount.getId();

        return writerId == accountId;
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("content", new Content());
        return "post/write";
    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute Content content, Errors errors,
                        Principal principal) {
        if (errors.hasErrors()) {
            return "post/write";
        }

        String name = principal.getName();
        contentService.saveContent(content, name);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long content_id) {
        Optional<Content> selectedContent = contentRepository.findById(content_id);
        contentRepository.delete(selectedContent.get());

        return "index";
    }

    @GetMapping("/modify")
    public String modify(Model model, @RequestParam Long content_id) {
        model.addAttribute("content_id", content_id);

        Optional<Content> selectedContent = contentRepository.findById(content_id);
        model.addAttribute("content", selectedContent.get());
        return "/post/modify";
    }

    @PostMapping("/modify")
    public String modify(@Valid @ModelAttribute Content content, Errors errors,
                         Model model, RedirectAttributes attributes,
                         @RequestParam Long content_id) {
        if (errors.hasErrors()) {
            model.addAttribute("content_id", content_id);
            return "/post/modify";
        }

        attributes.addAttribute("content_id", content_id);

        contentService.update(content, content_id);
        return "redirect:/post/read";
    }

}
