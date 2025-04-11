/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ParkingApiRest.controller;

import com.mycompany.parkingapi.model.Ticket;
import com.mycompany.parkingapi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author christianrieigl
 */
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    // GET - Obtener todos los tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // GET - Obtener un ticket por ID
    @GetMapping("/{id}")
    public Optional<Ticket> getTicketById(@PathVariable Integer id) {
        return ticketService.getTicketById(id);
    }

    // POST - Crear un nuevo ticket
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    // PUT - Actualizar un ticket existente
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Integer id, @RequestBody Ticket ticketDetails) {
        return ticketService.updateTicket(id, ticketDetails);
    }

    // DELETE - Eliminar un ticket por ID
    @DeleteMapping("/{id}")
    public boolean deleteTicket(@PathVariable Integer id) {
        return ticketService.deleteTicket(id);
    }
}
