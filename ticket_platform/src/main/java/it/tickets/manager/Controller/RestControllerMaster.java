package it.tickets.manager.Controller;

import java.util.List;

import it.tickets.manager.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.tickets.manager.Model.CategoriesModel;
import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.TicketState;
import it.tickets.manager.Service.ITicketService;


@RestController
public class RestControllerMaster {

    @Autowired
    ITicketService ticketService;
    @Autowired
    ICategoryService categoryService;


    @GetMapping("/ticketsApi")
    public ResponseEntity<List<TicketModel>> getTicketModel() {

        List<TicketModel> tickets = ticketService.getAllTickets();
        /*controllare se si deve richiamare il service o la sua interfaccia */
        if (tickets != null) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }

    @GetMapping("/ticketsApi/{idCategory}")/*Controlla */
    public ResponseEntity<List<TicketModel>> getTicketForCategory(@PathVariable("idCategory") Integer id) {
        CategoriesModel category = categoryService.findCategory(id);
        List<TicketModel> tickesfindBycategory = ticketService.searchByCategory(category);

        if (tickesfindBycategory != null) {
            return new ResponseEntity<>(tickesfindBycategory, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/ticketsApi/state")
    public ResponseEntity<List<TicketModel>> searchTicketsByState(@RequestParam(name = "state") TicketState state) {
        List<TicketModel> ticketsByCategory = ticketService.searchByState(state);


        if (ticketsByCategory != null) {
            return new ResponseEntity<>(ticketsByCategory, HttpStatus.OK);
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


}
