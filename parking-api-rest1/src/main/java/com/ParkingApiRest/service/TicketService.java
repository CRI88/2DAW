/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ParkingApiRest.service;

import com.ParkingApiRest.model.Ticket;
import com.ParkingApiRest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author christianrieigl
 */

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Integer id) {
        return ticketRepository.findById(id);
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Integer id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));
        ticket.setMessage(ticketDetails.getMessage());
        ticket.setTitle(ticketDetails.getTitle());
        ticket.setName(ticketDetails.getName());
        ticket.setAddress(ticketDetails.getAddress());
        ticket.setTelephone(ticketDetails.getTelephone());
        ticket.setEnter_date(ticketDetails.getEnter_date());
        ticket.setEnter_hour(ticketDetails.getEnter_hour());
        ticket.setLicense(ticketDetails.getLicense());
        ticket.setAssigned_place(ticketDetails.getAssigned_place());
        return ticketRepository.save(ticket);
    }

    public boolean deleteTicket(Integer id) {
        return ticketRepository.findById(id).map(ticket -> {
            ticketRepository.delete(ticket);
            return true;
        }).orElse(false);
    }
}
