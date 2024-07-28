package it.tickets.manager.Controller;

import it.tickets.manager.Model.NoteModel;
import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Service.INoteService;
import it.tickets.manager.Service.ITicketService;
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

    @GetMapping("/{id}")
    public String showNotes(@PathVariable("id") Integer id, Model model) {
        List<NoteModel> notes =noteService.showAllNotesByTicketId(id);
        model.addAttribute("notes", notes);
        return "detailPage";
    }

    @GetMapping("/{id}/addNote")
    public String addNote(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("note", new TicketModel());
        return "ticket";
    }

    @PostMapping("/{id}/addNote")
    public String addNote(@PathVariable("id") Integer Ticketid, @ModelAttribute("note") NoteModel note, BindingResult result) {
        if (result.hasErrors()) {
            return "ticket";
        }
        noteService.addNoteInTicket(Ticketid, note);

        return "ticket";

    }
}
