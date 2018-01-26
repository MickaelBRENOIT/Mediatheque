package com.mickaelbrenoit.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mickaelbrenoit.business.model.Item;
import com.mickaelbrenoit.business.service.CategoryService;
import com.mickaelbrenoit.business.service.ItemService;

@Controller
@RequestMapping("/search")
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * 
	 * Permet de lister tous les produits (le fait de les afficher seulement s'ils sont actifs est fait avec thymeleaf)
	 * 
	 * */
	@RequestMapping(method = RequestMethod.GET)
	public String listAllItems(Model model) {
		model.addAttribute("items", itemService.findAll());
//		model.addAttribute("categories", categoryService.findAll());
		return "search/listofitems";
	}
	
	/*
	 * 
	 * Permet de récupérer tous les produits appartenant à la catégorie demandée par l'utilisateur
	 * 
	 * */
	@RequestMapping(value = "/itemsbycategory", method = RequestMethod.GET)
	public String searchItemsByCategory(@RequestParam(value="select-category", required=true) String searchName, @RequestParam(value="userId", required=false, defaultValue="") String userId, Model model) {
//		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("items", itemService.findAllByCategoryName(searchName));
		if(!userId.equals("")) {
			model.addAttribute("userId", userId);
		}
		return "search/listofitems";
	}
	
}
