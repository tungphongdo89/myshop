package com.tungphongdo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tungphongdo.repository.ContactRepository;

@Controller
@RequestMapping("/manageContact")
public class ManageContactController {
	@Autowired
	private ContactRepository contactRepository;
	
	@GetMapping("/{page}/{size}")
	public String manageContact(Model model, @PathVariable int page, @PathVariable int size) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = contactRepository.findContacts(pageable).getTotalPages();
		
		model.addAttribute("listContacts", contactRepository.findContacts(pageable));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "a");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageContact";
	}
	
	@GetMapping("/search/{page}/{size}")
	public String searchManageContact(Model model, @PathVariable int page, @PathVariable int size,
			@RequestParam("name") String name) {
		Pageable pageable = PageRequest.of(page, size);
		int pageNumber = pageable.getPageNumber();
		
		int totalPage = contactRepository.findContacts(pageable).getTotalPages();
		
		model.addAttribute("listContacts", contactRepository.findContactsWithPartOfName(name));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", "a");
		
		int pageSize = 5;
		model.addAttribute("pageSize", pageSize);
		
		return "Admin/ManageContact";
	}
	
}
