package com.mickaelbrenoit.web.controller;

import java.util.Date;
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

import com.mickaelbrenoit.business.model.Item;
import com.mickaelbrenoit.business.model.User;
import com.mickaelbrenoit.business.service.CategoryService;
import com.mickaelbrenoit.business.service.ItemService;
import com.mickaelbrenoit.business.service.RoleService;
import com.mickaelbrenoit.business.service.TypeItemService;
import com.mickaelbrenoit.business.service.UserService;
import com.mickaelbrenoit.utils.PasswordUtils;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 
	 * 
	 * 			BEGIN --- USER PART
	 * 
	 * 
	 * */

	@RequestMapping(value="/listusers", method = RequestMethod.GET)
	public String listOfEmployees(Model model) {
		
		List<User> allUsers = userService.findAllByRole("USER");
		model.addAttribute("users", allUsers);
		
		return "emp/listofusers";
	}
	
	@RequestMapping(value="/adduser", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("role", roleService.findByName("USER"));
		return "emp/adduser";
	}
	
	@RequestMapping(value="/adduser", method = RequestMethod.POST)
	public String formAddEmployee(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
		
		User alreadyExists = userService.findByLogin(user.getLogin());
		
		if (bindingResult.hasErrors() || alreadyExists != null) {
			model.addAttribute("role", roleService.findByName("USER"));
			if(alreadyExists != null) {
				model.addAttribute("loginalreadyexists", user.getLogin());
			}
            return "emp/adduser";
        }

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		
		userService.save(user);
		
		return "redirect:/emp/listusers";
	}
	
	@RequestMapping(value="/edituser", method = RequestMethod.GET)
	public String editEmployee(@RequestParam("id") Long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("currentlogin", user.getLogin());
		model.addAttribute("role", roleService.findByName("USER"));
		return "emp/edituser";
	}
	
	@RequestMapping(value="/edituser", method = RequestMethod.POST)
	public String formEditEmployee(@Valid @ModelAttribute User user, BindingResult bindingResult, @RequestParam(value="currentlogin") String currentLogin, Model model) {
		
		User alreadyExists = userService.findByLogin(user.getLogin());
		
		if(alreadyExists != null && !alreadyExists.getLogin().equals(currentLogin)) {
			model.addAttribute("role", roleService.findByName("USER"));
			model.addAttribute("currentlogin", currentLogin);
			model.addAttribute("loginalreadyexists", user.getLogin());
			return "emp/edituser";
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("role", roleService.findByName("USER"));
			model.addAttribute("currentlogin", currentLogin);
            return "emp/edituser";
        }

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		
		userService.save(user);
		
		return "redirect:/emp/listusers";
	}
	
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable("id") Long id) {
		userService.delete(id);
		return "redirect:/emp/listusers";
	}
	
	
	/**
	 * 
	 * 
	 * 			END --- USER PART
	 * 
	 * 
	 * */
	
	/**
	 * 
	 * 
	 * 			BEGIN --- ITEM PART
	 * 
	 * 
	 * */
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private TypeItemService typeItemService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/listitems", method = RequestMethod.GET)
	public String listOfItems(Model model) {
		
		List<Item> allItems = itemService.findAll();
		model.addAttribute("items", allItems);
		
		return "emp/listofitems";
	}
	
	@RequestMapping(value="/additem", method = RequestMethod.GET)
	public String addItem(Model model) {
		model.addAttribute("typeitems", typeItemService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("item", new Item());
		return "emp/additem";
	}
	
	@RequestMapping(value="/additem", method = RequestMethod.POST)
	public String formAddItem(@Valid @ModelAttribute Item item, BindingResult bindingResult, Model model) {
		
		Item itemAlreadyExists = itemService.findByUniversalProductCode(item.getUniversalProductCode());
		
		if (bindingResult.hasErrors() || itemAlreadyExists != null) {
			model.addAttribute("typeitems", typeItemService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			if(itemAlreadyExists != null) {
				model.addAttribute("upcalreadyexists", item.getUniversalProductCode());
			}
            return "emp/additem";
        }
		
		itemService.save(item);
		
		return "redirect:/emp/listitems";
		
	}
	
	@RequestMapping(value="/edititem", method = RequestMethod.GET)
	public String editItem(@RequestParam("id") Long id, Model model) {
		model.addAttribute("typeitems", typeItemService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("currentupc", item.getUniversalProductCode());
		return "emp/edititem";
	}
	
	@RequestMapping(value="/edititem", method = RequestMethod.POST)
	public String formEditItem(@Valid @ModelAttribute Item item, BindingResult bindingResult, @RequestParam(value="currentupc") Long currentUpc, Model model) {
		
		Item itemAlreadyExists = itemService.findByUniversalProductCode(item.getUniversalProductCode());
		
		LOGGER.info("ITEM ALREADY UPC : " + itemAlreadyExists.getUniversalProductCode());
		LOGGER.info("ITEM PARAM   UPC : " + currentUpc);
		
		if(itemAlreadyExists != null && !itemAlreadyExists.getUniversalProductCode().equals(currentUpc)) {
			LOGGER.info("THIS ITEM ALREADY EXISTS !!!");
			model.addAttribute("typeitems", typeItemService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("upcalreadyexists", item.getUniversalProductCode());
			model.addAttribute("currentupc", currentUpc);
			return "emp/edititem";
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("typeitems", typeItemService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("currentupc", currentUpc);
            return "emp/edititem";
        }
		
		itemService.save(item);
		
		return "redirect:/emp/listitems";
	}
	
	@RequestMapping(value = "/deleteitem/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") Long id) {
		itemService.delete(id);
		return "redirect:/emp/listitems";
	}
	
}
