package com.glearning.ticketTrackerApp.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glearning.ticketTrackerApp.entity.Ticket;
import com.glearning.ticketTrackerApp.repository.TicketRepository;
import com.glearning.ticketTrackerApp.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getTicketById(Long id) {
		return ticketRepository.findById(id).orElse(null);
	}

	@Override
	public Ticket createTicket(Ticket ticket) {
		ticket.setDate(new Date());
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket updateTicket(Long id, Ticket ticket) {
		Ticket existingTicket = ticketRepository.findById(id).orElse(null);
		if (existingTicket != null) {
			existingTicket.setTitle(ticket.getTitle());
			existingTicket.setDescription(ticket.getDescription());
			existingTicket.setContent(ticket.getContent());
			return ticketRepository.save(existingTicket);
		}
		return null;
	}

	@Override
	public void deleteTicket(Long id) {
		ticketRepository.deleteById(id);
	}

	@Override
	public List<Ticket> searchTickets(String query) {
		return ticketRepository.findByTitleContainingIgnoreCase(query);
	}
}
