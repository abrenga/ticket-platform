package it.tickets.manager.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ticket")
public class TicketModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @DateTimeFormat(pattern = "dd-MM-YYYY")
    @Column( nullable = false)
    private LocalDate date;


    @Column
    private String text;



    private TicketState state;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private CategoriesModel category;


    @OneToMany(mappedBy = "id")
    private List<NoteModel> notes;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserModel user;

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    public CategoriesModel getCategory() {
        return category;
    }

    public void setCategory(CategoriesModel category) {
        this.category = category;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
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

    public void setState(TicketState state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public TicketState getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }
}