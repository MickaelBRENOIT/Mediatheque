package com.mickaelbrenoit.web.controller;

import java.time.LocalDate;
import java.time.ZoneId;
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
import com.mickaelbrenoit.business.model.Loan;
import com.mickaelbrenoit.business.model.User;
import com.mickaelbrenoit.business.repository.ItemRepository;
import com.mickaelbrenoit.business.service.CategoryService;
import com.mickaelbrenoit.business.service.ItemService;
import com.mickaelbrenoit.business.service.LoanService;
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

	/*
	 * 
	 * Permet d'afficher la liste des utilisateurs du site
	 * 
	 * */
	@RequestMapping(value="/listusers", method = RequestMethod.GET)
	public String listOfEmployees(Model model) {
		
		List<User> allUsers = userService.findAllByRole("USER");
		model.addAttribute("users", allUsers);
		
		return "emp/listofusers";
	}
	
	/*
	 * 
	 * Permet d'initialiser le formulaire d'ajout d'un utilisateur
	 * 
	 * */
	@RequestMapping(value="/adduser", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("role", roleService.findByName("USER"));
		return "emp/adduser";
	}
	
	/*
	 * 
	 * Permet de traiter les informations du formulaire d'ajout d'un utilisateur
	 * 
	 * */
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
		user.setActive(true);
		
		userService.save(user);
		
		return "redirect:/emp/listusers";
	}
	
	/*
	 * 
	 * Permet de changer le statut d'un compte utilisateur (Activation/Désactivation)
	 * 
	 * */
	@RequestMapping(value="/accountstate", method = RequestMethod.GET)
	public String changeAccountState(@RequestParam("id") Long id, Model model) {
		User user = userService.findById(id);
		user.setActive(!user.isActive());
		userService.save(user);
		return "redirect:/emp/listusers";
	}
	
	/*
	 * 
	 * Permet de traiter les informations de l'édition d'un utilisateur
	 * 
	 * */
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
	
	/*
	 * 
	 * Permet d'initiliser l'édition d'un utilisateur
	 * 
	 * */
	@RequestMapping(value="/edituser", method = RequestMethod.GET)
	public String editEmployee(@RequestParam("id") Long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("currentlogin", user.getLogin());
		model.addAttribute("role", roleService.findByName("USER"));
		return "emp/edituser";
	}
	
	/*
	 * 
	 * Permet de supprimer un utilisateur
	 * 
	 * */
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
	
	/*
	 * 
	 * Permet d'afficher la liste des produits
	 * 
	 * */
	@RequestMapping(value="/listitems", method = RequestMethod.GET)
	public String listOfItems(Model model) {
		
		List<Item> allItems = itemService.findAll();
		model.addAttribute("items", allItems);
		
		return "emp/listofitems";
	}
	
	/*
	 * 
	 * Permet d'initiliser le formulaire d'ajout d'un produit
	 * 
	 * */
	@RequestMapping(value="/additem", method = RequestMethod.GET)
	public String addItem(Model model) {
		model.addAttribute("typeitems", typeItemService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("item", new Item());
		return "emp/additem";
	}
	
	/*
	 * 
	 * Permet de traiter les informations du formulaire d'ajout du produit
	 * 
	 * */
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
	
	/*
	 * 
	 * Permet d'initiliser l'édition d'un produit
	 * 
	 * */
	@RequestMapping(value="/edititem", method = RequestMethod.GET)
	public String editItem(@RequestParam("id") Long id, Model model) {
		model.addAttribute("typeitems", typeItemService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("currentupc", item.getUniversalProductCode());
		return "emp/edititem";
	}
	
	/*
	 * 
	 * Permet de traiter les informations d'édition d'un produit
	 * 
	 * */
	@RequestMapping(value="/edititem", method = RequestMethod.POST)
	public String formEditItem(@Valid @ModelAttribute Item item, BindingResult bindingResult, @RequestParam(value="currentupc") Long currentUpc, Model model) {
		
		Item itemAlreadyExists = itemService.findByUniversalProductCode(item.getUniversalProductCode());
		
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
	
	/*
	 * 
	 * Permet de changer le statut d'un produit (Activation/Désactivation)
	 * 
	 * */
	@RequestMapping(value = "/itemstate", method = RequestMethod.GET)
	public String changeItemState(@RequestParam("idItem") String idItem, Model model) {
		
		Item item = itemService.findById(Long.parseLong(idItem));
		item.setActive(!item.isActive());
		itemService.save(item);
		
		return "redirect:/search";
	}
	
	/*
	 * 
	 * Permet la suppression d'un produit
	 * 
	 * */
	@RequestMapping(value = "/deleteitem/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") Long id) {
		itemService.delete(id);
		return "redirect:/emp/listitems";
	}
	
	/**
	 * 
	 * 
	 * 			END --- ITEM PART
	 * 
	 * 
	 **/
	
	/**
	 * 
	 * 
	 * 			BEGIN --- LOAN PART
	 * 
	 * 
	 * */
	
	@Autowired
	private LoanService loanService;
	
	/*
	 * 
	 * Permet d'afficher la liste des emprunts
	 * 
	 * */
	@RequestMapping(value="/listloans", method = RequestMethod.GET)
	public String listAllLoansByUserId(@RequestParam("id") Long id, Model model) {
		model.addAttribute("loans", loanService.findAllLoansByUserId(id));
		model.addAttribute("userId", id);
		return "emp/listofloans";
	}
	
	/*
	 * 
	 * Permet d'initialiser le formulaire d'ajout d'un emprunt
	 * 
	 * */
	@RequestMapping(value="/addloan", method = RequestMethod.GET)
	public String addLoanForUser(@RequestParam("id") Long id, Model model) {
		model.addAttribute("items", itemService.findAll());
		model.addAttribute("userId", id);
		return "search/listofitems";
	}
	
	/*
	 * 
	 * Permet de traiter l'ajout d'un emprunt à un utilisateur
	 * On décrémente aussi le nombre de quantité du produit qui a été emprunté
	 * 
	 * */
	@RequestMapping(value="/addloanuser", method = RequestMethod.GET)
	public String addLoanForAnUser(@RequestParam("idUser") String idUser, @RequestParam("idItem") String idItem, Model model) {
		
		User user = userService.findById(Long.parseLong(idUser));
		Item item = itemService.findById(Long.parseLong(idItem));
		
		item.setQuantity(item.getQuantity()-1);
		itemService.save(item);
		
		Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(LocalDate.now().plusMonths(item.getTypeItem().getDurationLoan()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Loan loan = new Loan(startDate, endDate, null, user, item);
		
		loanService.save(loan);
		
		model.addAttribute("id", user.getIdUser());
		
		return "redirect:/emp/listloans";
	}
	
	/*
	 * 
	 * Permet de traiter le rendu d'un prêt d'un produit
	 * On incrémente aussi la quantité du produit qui a été rendu
	 * 
	 * */
	@RequestMapping(value = "/givenbackloan", method = RequestMethod.GET)
	public String givenBackLoan(@RequestParam("idUser") String idUser, @RequestParam("idLoan") String idLoan, Model model) {
		
		Loan loan = loanService.findById(Long.parseLong(idLoan));
		Item item = itemService.findById(loan.getItem().getIdItem());
		
		item.setQuantity(item.getQuantity()+1);
		loan.setGivenBackDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		itemService.save(item);
		loanService.save(loan);
		
		model.addAttribute("id", Long.parseLong(idUser));
		
		return "redirect:/emp/listloans";
	}
	
	/*
	 * 
	 * Permet de supprimer un emprunt (si nous n'avons plus besoin de le voir apparaître dans l'historique des emprunts)
	 * 
	 * */
	@RequestMapping(value = "/deleteloan", method = RequestMethod.GET)
	public String deleteLoan(@RequestParam("idUser") String idUser, @RequestParam("idLoan") String idLoan, Model model) {
		loanService.delete(Long.parseLong(idLoan));
		model.addAttribute("id", Long.parseLong(idUser));
		return "redirect:/emp/listloans";
	}
	
}
