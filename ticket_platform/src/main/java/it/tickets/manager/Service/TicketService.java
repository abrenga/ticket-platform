package it.tickets.manager.Service;

import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {

    @Autowired
    public TicketsRepository ticketRepository;

    @Autowired
    public categoryService categoryService;

    @Autowired
    public UserService userService;

    public List<TicketModel> getAllTickets() {
        List<TicketModel> tickets = ticketRepository.findAll();
        return tickets;
    }

    public TicketModel findByTicketId(Integer id) {
        TicketModel ticket = ticketRepository.findById(id).get();
        return ticket;
    }

    public void saveTicket(TicketModel ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Integer id){
        TicketModel ticket = ticketRepository.findById(id).get();
        ticketRepository.delete(ticket);
    }


    public List<TicketModel> showTicketsByUsderId(UserModel user) {
        List<TicketModel> ticketRelativeUser = ticketRepository.findByUser(user);
        return ticketRelativeUser;
    }

    public List<TicketModel> searchByTitle(String title) {
        List<TicketModel>  ticket = ticketRepository.findByTitleLike(title);
        return ticket;
    }





}
