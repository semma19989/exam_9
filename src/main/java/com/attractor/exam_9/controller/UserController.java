package com.attractor.exam_9.controller;


import com.attractor.exam_9.exception.PaswordNotFoundException;
import com.attractor.exam_9.model.Pasword;
import com.attractor.exam_9.model.User;
import com.attractor.exam_9.model.UserRegistor;
import com.attractor.exam_9.repository.PaswordRepository;
import com.attractor.exam_9.repository.UserRepository;
import com.attractor.exam_9.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository repository;
    private final PaswordRepository paswordRepository;
    private com.attractor.exam_9.model.User User;

    @GetMapping("/profile")
    public String pageAuthorProfile(Model model, Principal principal)
    {
        var user = UserService.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }

    @GetMapping("/register")
    public String pageRegisterAuthor(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new UserRegistor());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid UserRegistor authorRequestDto,
                               BindingResult validationResult,
                               RedirectAttributes attributes) {
        attributes.addFlashAttribute("dto", authorRequestDto);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }
        userService.register(authorRequestDto);
        return "redirect:/login";
    }

    @GetMapping("/mail-password")
    public String pageForgotPassword(Model model) {
        return "mail";
    }

    @PostMapping("/mail-password")
    public String submitForgotPasswordPage(@RequestParam("email") String email,
                               RedirectAttributes attributes) {

        if (!repository.existsByEmail(email)) {
            attributes.addFlashAttribute("errorText", "Entered email does not exist!");
            return "redirect:/";
        }

        Pasword pToken = Pasword.builder()
                .user(repository.findByEmail(email).get())
                .topic(UUID.randomUUID().toString())
                .build();

        PaswordRepository PaswordRepository;
        PaswordRepository.deleteAll();
        PaswordRepository.save(pToken);

        return "redirect:/mail-success";
    }

    @GetMapping("/mail-success")
    public String pageResetPassword(Model model) {
        return "mail-success";
    }

    @PostMapping("/reset-password")
    public String submitResetPasswordPage(@RequestParam("token") String token,
                                          @RequestParam("newPassword") String newPassword,
                                           RedirectAttributes attributes) {

        if (!PaswordRepository.existsByToken(token)) {
            attributes.addFlashAttribute("errorText", "Entered email does not exist!");
            return "redirect:/reset-password";
        }
        Pasword  = PaswordRepository.findByToken(token).get();
        User = repository.findById(Pasword.getUser().getId()).get();
        user.setPasword(new BCryptPasswordEncoder().encode(newPassword));
        repository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }



    @ExceptionHandler(PaswordNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(PaswordNotFoundException ex, Model model) {

        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }
}
