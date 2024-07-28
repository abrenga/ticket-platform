package it.tickets.manager.Controller;


import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Service.ITicketService;
import it.tickets.manager.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    public ITicketService ticketService;
    @Autowired
    private UserService userService;

    /*visualizza tutti i ticket nel database*/
    @GetMapping
    public String showTickets(Model model) {
        model.addAttribute("searchText", new TicketModel());
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "userPage";
    }

  @PostMapping("/searchTickets")
  public String searchTickets(@Valid @ModelAttribute("searchText") TicketModel ticketModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "userPage";
        }
        List<TicketModel> tickets = ticketService.searchByTitle(ticketModel.getTitle());
        return "userPage";
  }





    @GetMapping("user/{userId}")
        public String ticketOfIdUser(@PathVariable(name="userId") Integer id, Model model){
        UserModel UserEnable= userService.findTicketOfUser(id);
        List<TicketModel> ticket= ticketService.showTicketsByUsderId(UserEnable);
        model.addAttribute("ticketsOfIdUser", ticket);
        return "userPage";
        }

    @GetMapping("{id}")
    public String showTicketDetails(@PathVariable("id") Integer id, Model model) {
        TicketModel ticket = ticketService.findByTicketId(id);
        model.addAttribute("showDetail", ticket);
        return "detailPage";

    }

    @GetMapping("createTicket")
    public String createTicket(Model model) {
        model.addAttribute("ticket", new TicketModel());
        model.addAttribute("updateTicket", false);
        return "formPage";
    }

    /*controlla se mette tutto nel form da solo */
    @PostMapping("createTicket")
    public String createTicket(@Valid @ModelAttribute(name = "ticket") TicketModel ticket, @RequestParam(name = "id_user") Integer idUser, @RequestParam(name = "id_category") Integer idCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userPage";
        }
        ticketService.saveTicket(ticket);
        return "redirect:/userPage";
    }


    @GetMapping("updateTicket/{id}")
    public String updateTicket(@PathVariable("id") Integer id, Model model) {
        TicketModel ticket = ticketService.findByTicketId(id);
        model.addAttribute("updateTicket", ticket);
        model.addAttribute("up", true);
        return "formPage";
    }


    @PutMapping("updateTicket/{id}")
    public String updateTicket(@Valid @ModelAttribute(name = "updateTicket") TicketModel ticket, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formPage";
        }

        ticketService.saveTicket(ticket);
        return "redirect:/index";
    }

    @DeleteMapping("delete/{id}")
    public String deleteTicket(@PathVariable("id") Integer id, Model model) {
        ticketService.deleteTicket(id);
        return "redirect:/userPage";
    }


}