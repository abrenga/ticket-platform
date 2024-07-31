package it.tickets.manager.Controller;


import it.tickets.manager.Model.NoteModel;
import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.TicketState;
import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Security.DatabaseUserDetails;
import it.tickets.manager.Service.INoteService;
import it.tickets.manager.Service.ITicketService;
import it.tickets.manager.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

    private boolean hasAuthority(Authentication authentication, String role) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(role)) {
                return true;
            }
        }

        return false;
    }

    @GetMapping("afterLogin")
    public String onAfterLogin(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            if (hasAuthority(authentication, "ADMIN")) {
                return "redirect:/tickets";
            } else {
                DatabaseUserDetails userDetails = (DatabaseUserDetails) authentication.getPrincipal();
                return "redirect:/tickets/user/" + userDetails.getId();
            }
        } else {
            return "redirect:/login";
        }
    }

    /*visualizza tutti i ticket nel database*/
    @GetMapping
    public String showTickets(Authentication authentication, Model model) {
        //model.addAttribute("searchText", new TicketModel());
        model.addAttribute("allTickets", ticketService.getAllTickets());
        model.addAttribute("authenticator", authentication);
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
    public String ticketOfIdUser(@PathVariable(name = "userId") Integer id, Authentication authentication, Model model) {
        UserModel UserEnable= userService.findTicketOfUser(id);
        List<TicketModel> ticket= ticketService.showTicketsByUsderId(UserEnable);
        model.addAttribute("allTickets", ticket);
        model.addAttribute("authenticator", authentication);
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
    public String createTicket(Model model,Authentication authentication) {
        model.addAttribute("ticket", new TicketModel());
        model.addAttribute("categories",categoryService.showCAtegory());
        model.addAttribute("states", TicketState.class);
        model.addAttribute("operators", userService.showUser());
        model.addAttribute("up", false);
        model.addAttribute("isAdmin", hasAuthority(authentication,"ADMIN"));
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
    public String updateTicket(Authentication authentication, @PathVariable("id") Integer id, Model model) {
        TicketModel ticket = ticketService.findByTicketId(id);
        model.addAttribute("categories",categoryService.showCAtegory());
        model.addAttribute("states", TicketState.values());
        model.addAttribute("operators", userService.showUser());
        model.addAttribute("ticket", ticket);
        model.addAttribute("authenticator", authentication);
        model.addAttribute("up", true);
        model.addAttribute("isAdmin", hasAuthority(authentication,"ADMIN"));
        return "formPage";
    }


    @PostMapping("updateTicket/{id}")
    public String updateTicket(@Valid @ModelAttribute(name = "ticket") TicketModel ticket, Authentication authentication, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories",categoryService.showCAtegory());
            model.addAttribute("states", TicketState.values());
            model.addAttribute("operators", userService.showUser());
            model.addAttribute("ticket", ticket);
            model.addAttribute("up", true);
            return "formPage";
        }
        if (authentication.isAuthenticated()) {
            if (hasAuthority(authentication, "ADMIN")) {
                ticketService.saveTicket(ticket);
            } else {
                TicketModel originalTicket = ticketService.findByTicketId(ticket.getId());
                originalTicket.setState(ticket.getState());
                ticketService.saveTicket(originalTicket);
            }
        }
        if (hasAuthority(authentication, "ADMIN")) {
            return "redirect:/tickets";
        }else {
            return "redirect:/tickets/user/"+ ((DatabaseUserDetails)authentication.getPrincipal()).getId();
        }
    }

    @PostMapping("delete/{id}")
    public String deleteTicket(@PathVariable("id") Integer id, Model model) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }

    //aggiunta del nome del username
    @GetMapping("/user")
    public String index(Authentication authentication, Model model) {
//        model.addAttribute("authenticator", authentication);
//        model.addAttribute("isAuthenticated", authentication.isAuthenticated());
        DatabaseUserDetails userDetails = (DatabaseUserDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", userDetails);
        UserModel user = userService.findUserByName(userDetails.getUsername());
        model.addAttribute("showButton", ticketService.canChangeAvailability(user));
        model.addAttribute("goHome", user.getId());
        model.addAttribute("available", user.getIsAvailable() ? "disponibile" : "offline");
        return "DetailUserPage";
    }

    @PostMapping("/changeUserState")
    public String changeUserState(Authentication authentication) {
        DatabaseUserDetails userDetails = (DatabaseUserDetails) authentication.getPrincipal();
        UserModel user = userService.findUserByName(userDetails.getUsername());
        if (user.getAvailable()) {
            user.setIsAvailable(false);
        } else {
            user.setIsAvailable(true);
        }
        userService.save(user);
        return "redirect:/tickets/user";
    }
  
  



}