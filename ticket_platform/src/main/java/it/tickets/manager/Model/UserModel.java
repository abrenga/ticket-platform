package it.tickets.manager.Model;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;
    private Boolean isAvailable;
    @OneToMany(mappedBy = "id")
    private List<NoteModel> notes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    public Boolean getAvailable() {
        return isAvailable;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsAvailable(Boolean isAviabele) {
        this.isAvailable = isAviabele;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }
}