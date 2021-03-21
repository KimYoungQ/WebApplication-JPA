package com.webapplication.main;

import com.webapplication.content.Content;
import com.webapplication.content.ContentRepository;
import com.webapplication.content.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ContentService contentService;

    @GetMapping("/")
    public String main(Model model, @PageableDefault Pageable pageable) {

        Page<Content> contentPage = contentService.getContentList(pageable);
        model.addAttribute("contentPage", contentPage);

        return "index";
    }
}
