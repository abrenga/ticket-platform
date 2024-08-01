package it.tickets.manager.Controller;

import it.tickets.manager.Model.NoteModel;
import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Security.DatabaseUserDetails;
import it.tickets.manager.Service.INoteService;
import it.tickets.manager.Service.ITicketService;
import it.tickets.manager.Service.IUserService;
import it.tickets.manager.Service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private INoteService noteService;
    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IUserService userService;


    @GetMapping("/{id}/addNote")
    public String addNote(@PathVariable("id") Integer id, Model model) {
        TicketModel ticket = ticketService.findByTicketId(id);
        model.addAttribute("user",ticket.getUser());
        model.addAttribute("ticket",ticket);
        NoteModel note = new NoteModel();
        note.setUser(ticket.getUser());
        model.addAttribute("note",note);


        return "formNote";
    }

    @PostMapping("/{ticketId}/addNote")
    public String addNote(@PathVariable("ticketId") Integer ticketid, @Valid @ModelAttribute("note") NoteModel note, Authentication authentication, BindingResult result, Model model) {
        if (note.getNotes().isBlank()) {
            result.addError(new ObjectError("notes", "notes non può essere vuoto"));
        }

        if (result.hasErrors()) {
            TicketModel ticket = ticketService.findByTicketId(ticketid);
            model.addAttribute("user",ticket.getUser());
            model.addAttribute("ticket",ticket);
            note.setUser(ticket.getUser());
            return "formNote";
        }

        DatabaseUserDetails userDetails =((DatabaseUserDetails)authentication.getPrincipal());
        UserModel usermodel= userService.findUser(userDetails.getId());
        note.setUser(usermodel);
        noteService.addNoteInTicket(ticketid, note);

        return "redirect:/tickets/"+ticketid;

    }
}
