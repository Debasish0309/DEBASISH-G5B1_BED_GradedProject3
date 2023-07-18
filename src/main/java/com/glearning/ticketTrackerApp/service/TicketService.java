package com.glearning.ticketTrackerApp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.glearning.ticketTrackerApp.entity.Ticket;

@Component
public interface TicketService {

	List<Ticket> getAllTickets();

	Ticket getTicketById(Long id);

	Ticket createTicket(Ticket ticket);

	Ticket updateTicket(Long id, Ticket ticket);

	void deleteTicket(Long id);

	List<Ticket> searchTickets(String query);

}
