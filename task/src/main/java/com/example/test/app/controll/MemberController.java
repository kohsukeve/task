package com.example.test.app.controll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.domain.form.MemberForm;
import com.example.test.domain.model.Member;
import com.example.test.domain.service.MemberService;

@Controller
@RequestMapping("/users")
public class MemberController {
	private boolean updatePrev = false;

	@Autowired
	private MemberService service;

	@GetMapping("/")
	public String index(Model model) {
		List<Member> list = service.findAll();
		model.addAttribute("list", list);
		return "members/index";
	}

	@PostMapping("/create/form/")
	public String createForm(@ModelAttribute MemberForm form, Model model) {
		return "members/create/form";
	}

	@PostMapping("/create/confirm/")
	public String createConfirm(@Validated @ModelAttribute MemberForm form, BindingResult result, Model model) {
		if (result.hasErrors())
			return createForm(form, model);
		return "members/create/confirm";
	}

	@PostMapping("/create/")
	public String create(@ModelAttribute MemberForm form, Model model) {
		Member user = new Member();
		user.setName(form.getName());
		user.setAge(form.getAge());
		service.save(user);
		return "redirect:/users/";
	}

	@PostMapping("/delete/confirm/{id}")
	public String deleteConfirm(@PathVariable Long id, Model model) {
		Optional<Member> optional = service.findById(id);
		if (optional.isPresent()) {
			model.addAttribute("user", optional.get());
			return "members/delete/confirm";
		} else {
			return "redirect:/users/";
		}
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		service.deleteById(id);
		return "redirect:/users/";
	}

	@PostMapping("/update/form/{id}")
	public String updateForm(@PathVariable Long id, @ModelAttribute MemberForm form, Model model) {
		if (!updatePrev) {
			Optional<Member> optional = service.findById(id);
			if (optional.isPresent()) {
				Member user = optional.get();
				System.out.println(user);
				form.setId(user.getId());
				form.setName(user.getName());
				form.setAge(user.getAge());
			} else {
				return "redirect:/users/";
			}
		}
		return "members/update/form";
	}

	@PostMapping("/update/confirm/")
	public String updateConfirm(@Validated @ModelAttribute MemberForm form, BindingResult result, Model model) {
		if (result.hasErrors())
			return updateForm(form.getId(), form, model);
		updatePrev = true;
		return "members/update/confirm";
	}

	@PostMapping("/update/")
	public String update(@ModelAttribute MemberForm form, Model model) {
		Member user = new Member();
		user.setId(form.getId());
		user.setName(form.getName());
		user.setAge(form.getAge());
		service.save(user);
		updatePrev = false;
		return "redirect:/users/";
	}

}