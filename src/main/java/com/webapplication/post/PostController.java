package com.webapplication.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final ContentRepository contentRepository;
    private final PostService postService;

    @GetMapping("/read")
    public String read(Model model, @RequestParam Long content_id) {
        Optional<Content> selectedContent = contentRepository.findById(content_id);
        model.addAttribute("content", selectedContent.get());
        return "post/read";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("content", new Content());
        return "post/write";
    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute Content content, Errors errors,
                        Principal principal) {
        if(errors.hasErrors()) {
            return "post/write";
        }

        String name = principal.getName();
        postService.saveContent(content, name);

        return "redirect:/";
    }
}
