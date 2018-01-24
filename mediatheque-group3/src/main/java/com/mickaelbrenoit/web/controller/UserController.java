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
import org.springframework.web.bind.annotation.RequestParam;

import com.mickaelbrenoit.business.model.User;
import com.mickaelbrenoit.business.service.LoanService;
import com.mickaelbrenoit.business.service.RoleService;
import com.mickaelbrenoit.business.service.UserService;
import com.mickaelbrenoit.utils.PasswordUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private LoanService loanService;
	
	@RequestMapping(value="/editprofile", method = RequestMethod.GET)
	public String editProfile(Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession(false);
		User user = userService.findById((Long) session.getAttribute("iduser"));
		model.addAttribute("user", user);
		model.addAttribute("currentlogin", user.getLogin());
		model.addAttribute("role", roleService.findByName("USER"));
		return "user/editprofile";
	}
	
	@RequestMapping(value="/editprofile", method = RequestMethod.POST)
	public String formEditProfile(@Valid @ModelAttribute User user, BindingResult bindingResult, @RequestParam(value="currentlogin") String currentLogin, Model model) {
		
		User alreadyExists = userService.findByLogin(user.getLogin());
		
		if(alreadyExists != null && !alreadyExists.getLogin().equals(currentLogin)) {
			model.addAttribute("role", roleService.findByName("USER"));
			model.addAttribute("currentlogin", currentLogin);
			model.addAttribute("loginalreadyexists", user.getLogin());
			return "user/editprofile";
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("role", roleService.findByName("USER"));
			model.addAttribute("currentlogin", currentLogin);
            return "user/editprofile";
        }

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		
		userService.save(user);
		
		return "redirect:/logout";
	}
	
	@RequestMapping(value="/myloans", method = RequestMethod.GET)
	public String usersLoans(Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession(false);
		model.addAttribute("loans", loanService.findAllLoansByUserId((Long) session.getAttribute("iduser")));
		return "user/myloans";
	}

}
