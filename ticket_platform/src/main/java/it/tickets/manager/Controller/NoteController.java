package it.tickets.manager.Controller;

import it.tickets.manager.Model.NoteModel;
import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Service.INoteService;
import it.tickets.manager.Service.ITicketService;
import it.tickets.manager.Service.IUserService;
import it.tickets.manager.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        NoteModel note =new NoteModel();
        note.setUser(ticket.getUser());
        model.addAttribute("note",note );


        return "formNote";
    }

    @PostMapping("/{id}/addNote")
    public String addNote(@PathVariable("id") Integer ticketid, @ModelAttribute("note") NoteModel note,BindingResult result) {
        if (result.hasErrors()) {
            return "formNote";
        }

        noteService.addNoteInTicket(ticketid, note);

        return "redirect:/tickets/"+ticketid;

    }
}
