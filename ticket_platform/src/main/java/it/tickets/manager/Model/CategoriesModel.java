package it.tickets.manager.Model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categories")
public class CategoriesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    @OneToMany(mappedBy="id")
    private List<TicketModel> tickets;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public Integer getId() {
        return id;
    }


    public List<TicketModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketModel> tickets) {
        this.tickets = tickets;
    }

}