package it.tickets.manager.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.tickets.manager.Model.CategoriesModel;
import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.TicketState;
import it.tickets.manager.Service.ITicketService;


@RestController
public class RestControllerMaster {

    @Autowired
    ITicketService ticketService;


    @GetMapping("/tickets")
    public  ResponseEntity<TicketModel> getTicketModel() {

         Optional<TicketModel> tickets = ticketService.getAllTickets();
        /*controllare se si deve richiamare il service o la sua interfaccia */
        if(tickets.isPresent()){
            return new ResponseEntity<>(tickets.get(),HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }

    @GetMapping("/tickets/category")/*Controlla */
    public ResponseEntity<TicketModel> getTicketForCategory(@RequestParam(name="category") CategoriesModel category) {
        
        Optional<TicketModel> tickesfindBycategory = ticketservice.searchByCategory(category);

        if(tickesfindBycategory.isPresent()){
            return new ResponseEntity<>(tickesfindBycategory.get(),HttpStatus.OK );
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    @GetMapping("/tickets/state")
public ResponseEntity<TicketModel> searchTicketsByCategory(@RequestParam(name="state") TicketModel state){
    Optional<TicketModel> ticketsByCategory= ticketService.searchByCategory(state);


    if(ticketsByCategory.isPresent()){
        return new ResponseEntity<>(ticketsByCategory.get(),HttpStatus.OK );
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

            


    



    

}
