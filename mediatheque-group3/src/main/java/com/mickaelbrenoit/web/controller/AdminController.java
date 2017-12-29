package com.mickaelbrenoit.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mickaelbrenoit.business.model.User;
import com.mickaelbrenoit.business.service.RoleService;
import com.mickaelbrenoit.business.service.UserService;
import com.mickaelbrenoit.utils.PasswordUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value="/listemployees", method = RequestMethod.GET)
	public String listOfEmployees(Model model) {
		
		List<User> allEmployees = userService.findAllByRole("EMP");
		model.addAttribute("employees", allEmployees);
		
		return "admin/listofemployees";
	}
	
	@RequestMapping(value="/addemployee", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("role", roleService.findByName("EMP"));
		return "admin/addemployee";
	}
	
	@RequestMapping(value="/addemployee", method = RequestMethod.POST)
	public String formAddEmployee(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
		
		User alreadyExists = userService.findByLogin(user.getLogin());
		
		if (bindingResult.hasErrors() || alreadyExists != null) {
			model.addAttribute("role", roleService.findByName("EMP"));
			if(alreadyExists != null) {
				model.addAttribute("loginalreadyexists", user.getLogin());
			}
            return "admin/addemployee";
        }

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		
		userService.save(user);
		
		return "redirect:/admin/listemployees";
	}
	
	@RequestMapping(value="/editemployee", method = RequestMethod.GET)
	public String editEmployee(@RequestParam("id") Long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		model.addAttribute("role", roleService.findByName("EMP"));
		return "admin/editemployee";
	}
	
	@RequestMapping(value="/editemployee", method = RequestMethod.POST)
	public String formEditEmployee(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
		
		User alreadyExists = userService.findByLogin(user.getLogin());
		
		if (bindingResult.hasErrors() || alreadyExists != null) {
			model.addAttribute("role", roleService.findByName("EMP"));
			if(alreadyExists != null) {
				model.addAttribute("loginalreadyexists", user.getLogin());
			}
            return "admin/editemployee";
        }

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		
		userService.save(user);
		
		return "redirect:/admin/listemployees";
	}
	
	@RequestMapping(value = "/deleteemployee/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") Long id) {
		userService.delete(id);
		return "redirect:/admin/listemployees";
	}
}
