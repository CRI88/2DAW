/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ParkingApiRest.repository;
import com.ParkingApiRest.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author christianrieigl
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
