/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ParkingApiRest.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author christianrieigl
 */
@Entity
@Table(name = "ticket")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ticket;
    private String message;
    private String title;
    private String name;
    private String address;
    private String telephone;
    private LocalDate enter_date;
    private LocalTime enter_hour;
    private String license;
    private String assigned_place;
}
