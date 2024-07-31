package it.tickets.manager.Controller;


import it.tickets.manager.Model.NoteModel;
import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.TicketState;
import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Service.INoteService;
import it.tickets.manager.Service.ITicketService;
import it.tickets.manager.Service.UserService;
import it.tickets.manager.Service.categoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    public ITicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private it.tickets.manager.Service.categoryService categoryService;
    @Autowired
    private INoteService noteService;

    /*visualizza tutti i ticket nel database*/
    @GetMapping
    public String showTickets(Model model) {
        //model.addAttribute("searchText", new TicketModel());
        model.addAttribute("allTickets", ticketService.getAllTickets());
        model.addAttribute("Admin", true);
        return "userPage";
    }

    @GetMapping("/searchTickets")
    public String searchTickets( @RequestParam("title") String title,Model model) {

        List<TicketModel> tickets = ticketService.searchByTitle(title);
        model.addAttribute("allTickets", tickets);
        model.addAttribute("title", title);


        return "userPage";
    }



    @GetMapping("user/{userId}")
        public String ticketOfIdUser(@PathVariable(name="userId") Integer id, Model model){
        UserModel UserEnable= userService.findTicketOfUser(id);
        List<TicketModel> ticket= ticketService.showTicketsByUsderId(UserEnable);
        model.addAttribute("allTickets", ticket);
        model.addAttribute("Admin", false);
        return "userPage";
        }

    @GetMapping("{id}")
    public String showTicketDetails(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("searchText", new TicketModel());
        TicketModel ticket = ticketService.findByTicketId(id);
        List<NoteModel> notes =noteService.showAllNotesByTicketId(id);
        model.addAttribute("notes", notes);
        model.addAttribute("showDetail", ticket);
        return "detailPage";

    }

    @GetMapping("createTicket")
    public String createTicket(Model model) {
        model.addAttribute("ticket", new TicketModel());
        model.addAttribute("categories",categoryService.showCAtegory());
        model.addAttribute("states", TicketState.class);
        model.addAttribute("operators", userService.showUser());
        model.addAttribute("up", false);
        return "formPage";
    }

    /*controlla se mette tutto nel form da solo */
    @PostMapping("createTicket")
    public String createTicket(@Valid @ModelAttribute(name = "ticket") TicketModel ticket, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formPage";
        }
        ticket.setDate(LocalDate.now());
        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }


    @GetMapping("updateTicket/{id}")
    public String updateTicket(@PathVariable("id") Integer id, Model model) {
        TicketModel ticket = ticketService.findByTicketId(id);
        model.addAttribute("categories",categoryService.showCAtegory());
        model.addAttribute("states", TicketState.values());
        model.addAttribute("operators", userService.showUser());
        model.addAttribute("ticket", ticket);
        model.addAttribute("up", true);
        return "formPage";
    }


    @PostMapping("updateTicket/{id}")
    public String updateTicket(@Valid @ModelAttribute(name = "ticket") TicketModel ticket, Model model,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories",categoryService.showCAtegory());
            model.addAttribute("states", TicketState.values());
            model.addAttribute("operators", userService.showUser());
            model.addAttribute("ticket", ticket);
            model.addAttribute("up", true);
            return "formPage";
        }

        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    @PostMapping("delete/{id}")
    public String deleteTicket(@PathVariable("id") Integer id, Model model) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }


}