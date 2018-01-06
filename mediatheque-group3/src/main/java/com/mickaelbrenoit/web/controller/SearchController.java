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
import com.mickaelbrenoit.business.service.ItemService;

@Controller
@RequestMapping("/search")
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private ItemService itemService;
	
	List<Item> items = new ArrayList<>();
	
	@RequestMapping(method = RequestMethod.GET)
	public String listAllItems(Model model) {
		List<Item> items = itemService.findAll();
		model.addAttribute("items", items);
		return "search/listofitems";
	}
	
//	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
//	public @ResponseBody
//	List<Item> getTags(@RequestParam String tagName) {
//
//		LOGGER.info("IN SEARCH RESULT METHOD");
//		
//		items.addAll(itemService.findAll());
//		List<Item> result = new ArrayList<>();
//		for (Item item : items) {
//			if (item.getCategory().getNameCategory().contains(tagName)) {
//				result.add(item);
//			}
//		}
//
//		return result;
//	}
}
