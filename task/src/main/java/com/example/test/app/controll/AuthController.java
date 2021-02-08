package com.example.test.app.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.domain.form.SignupForm;
import com.example.test.domain.service.AccountService;

@Controller
@RequestMapping("/")
public class AuthController {

	@Autowired
	private AccountService service;

	@GetMapping("/signup")
	public String signup(@ModelAttribute SignupForm form, Model model) {
		return "signup";
	}

	@PostMapping("/signup")
	public String regist(@Validated @ModelAttribute SignupForm form, BindingResult result, Model model) {
		if (result.hasErrors())
			return signup(form, model);
		service.registerAdmin(form.getUsername(), form.getPassword());
		return "redirect:/login";
	}

	@GetMapping("/members")
	public String memberIndex(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		model.addAttribute("username", name);
		return "redirect:/users/";

	}
}
