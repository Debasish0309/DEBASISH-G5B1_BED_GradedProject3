package com.glearning.ticketTrackerApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glearning.ticketTrackerApp.entity.Ticket;
import com.glearning.ticketTrackerApp.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("/list")
	public String getAllTickets(Model model) {
		List<Ticket> tickets = ticketService.getAllTickets();
		model.addAttribute("tickets", tickets);
		return "tickets/index";
	}

	@PostMapping("/{id}")
	public String getTicketById(@RequestParam("id") long id, Model model) {
		Ticket ticket = ticketService.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "tickets/show";
	}

	@GetMapping("/create")
	public String showCreateTicketForm(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "tickets/create";
	}

	@PostMapping("/create")
	public String createTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticketService.createTicket(ticket);
		return "redirect:/tickets/list";
	}

	@GetMapping("/edit")
	public String showEditTicketForm(@RequestParam("id") long id, Model model) {
		Ticket ticket = ticketService.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "tickets/edit";
	}

	@PostMapping("/edit")
	public String updateTicket(@RequestParam("id") long id, @ModelAttribute("ticket") Ticket ticket) {
		ticketService.updateTicket(id, ticket);
		return "redirect:/tickets/list";
	}

	@PostMapping("/{id}/delete")
	public String deleteTicket(@RequestParam("id") long id) {
		ticketService.deleteTicket(id);
		return "redirect:/tickets/list";
	}

	@PostMapping("/search")
	public String searchTickets(@RequestParam("query") String query, Model model) {
		List<Ticket> searchResults = ticketService.searchTickets(query);
		model.addAttribute("tickets", searchResults);
		return "tickets/index";
	}

}
