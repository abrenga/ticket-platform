package it.tickets.manager.Service;

import it.tickets.manager.Model.*;
import it.tickets.manager.Repository.NotesRepository;
import it.tickets.manager.Repository.TicketsRepository;
import it.tickets.manager.Security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService implements ITicketService {

    @Autowired
    public TicketsRepository ticketRepository;

    @Autowired
    public categoryService categoryService;

    @Autowired
    public UserService userService;
    @Autowired
    private NotesRepository notesRepository;

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
        List<NoteModel> note = ticket.getNotes();
        notesRepository.deleteAllInBatch(note);
        ticketRepository.delete(ticket);
    }

    public List<TicketModel> showTicketsByUsderId(UserModel user) {
        List<TicketModel> ticketRelativeUser = ticketRepository.findByUser(user);
        return ticketRelativeUser;
    }


    public String colorFill(){
        return "";
    }

    public List<TicketModel> searchByTitle(String title) {
        List<TicketModel>  tickets = ticketRepository.findByTitleLike("%"+title+"%");
        return tickets;
    }

    public List<TicketModel> searchByTitleAndUserId(String title, UserModel user) {
        List<TicketModel> tickets= ticketRepository.findByTitleLikeAndUser("%"+title+"%",user );
        return tickets;
    }

    public List<TicketModel> searchByCategory(CategoriesModel categoies) {
        List<TicketModel>  tickets = ticketRepository.findByCategory(categoies);
        return tickets;
    }

    public List<TicketModel> searchByState(TicketState state) {
        List<TicketModel>  tickets = ticketRepository.findByState(state);
        return tickets;
    }
    
    public boolean canChangeAvailability(UserModel user) {
    	List<TicketModel> tickets = showTicketsByUsderId(user);
    	
    	for(int i=0; i<tickets.size(); i++) {
    		TicketModel ticket =tickets.get(i) ;
    		if(ticket.getState()==TicketState.Open||ticket.getState()==TicketState.InProgress) {
    			return false;
    		}
    		
    	}
		return true;
    	
    }



}
