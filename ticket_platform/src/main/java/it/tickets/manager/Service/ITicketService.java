package it.tickets.manager.Service;

import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.TicketState;
import it.tickets.manager.Model.UserModel;

import java.util.List;

public interface ITicketService {
     List<TicketModel> getAllTickets();
      TicketModel findByTicketId(Integer id);

    void saveTicket(TicketModel ticket);


    void deleteTicket(Integer id);

    List<TicketModel> showTicketsByUsderId(UserModel user);

   List<TicketModel>  searchByTitle(String title);

   List<TicketModel>  searchByCategory(TicketState state);
}
