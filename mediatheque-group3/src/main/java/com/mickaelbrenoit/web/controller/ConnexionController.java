package com.mickaelbrenoit.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mickaelbrenoit.business.model.User;
import com.mickaelbrenoit.business.service.RoleService;
import com.mickaelbrenoit.business.service.UserService;
import com.mickaelbrenoit.utils.PasswordUtils;

@Controller
@RequestMapping("/")
public class ConnexionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnexionController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String GETSignUp(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("role", roleService.findByName("USER"));
		return "signup";
	}
	
	@RequestMapping(value="/signin", method = RequestMethod.GET)
	public String GETSignIn(Model model) {
		model.addAttribute("user", new User());
		return "signin";
	}

	@RequestMapping(value="/signin/form", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model, HttpServletRequest request, HttpSession session) {
		
		User loginexists = userService.findByLogin(user.getLogin());
		
		if(loginexists != null) {
			if(PasswordUtils.decodePassword(user.getPassword(), loginexists.getPassword())) {
				if(loginexists.isActive()) {
					session = request.getSession(true);
					session.setAttribute("iduser", loginexists.getIdUser());
					session.setAttribute("login", loginexists.getLogin());
					session.setAttribute("role", loginexists.getRole().getName());
					return "redirect:/";
				}
			}			
		}
		
		model.addAttribute("role", roleService.findByName("USER"));
		model.addAttribute("usernotfound", "test");
		return "signin";	
		
	}
	
	@RequestMapping(value="/signup/form", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) {
		
		User alreadyExists = userService.findByLogin(user.getLogin());
		
		if (bindingResult.hasErrors() || alreadyExists != null) {
			model.addAttribute("role", roleService.findByName("USER"));
			if(alreadyExists != null) {
				model.addAttribute("loginalreadyexists", user.getLogin());
			}
            return "signup";
        }

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		
		userService.save(user);
		
		session = request.getSession(true);
		session.setAttribute("iduser", user.getIdUser());
		session.setAttribute("login", user.getLogin());
		session.setAttribute("role", user.getRole().getName());
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpSession session) {
		session = request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
}
