package it.tickets.manager.Model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="note")
public class NoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String notes;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="ticket_id", nullable=false)
    private TicketModel ticket;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserModel user;


    public TicketModel getTicket() {
        return ticket;
    }

    public UserModel getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public void setTicket(TicketModel ticket) {
        this.ticket = ticket;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}