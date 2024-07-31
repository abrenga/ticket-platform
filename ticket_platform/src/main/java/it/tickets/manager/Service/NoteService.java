package it.tickets.manager.Service;

import it.tickets.manager.Model.NoteModel;
import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Repository.NotesRepository;
import it.tickets.manager.Repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements INoteService {
    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private TicketsRepository ticketRepository;

    public List<NoteModel> showAllNotesByTicketId(Integer id){
        TicketModel ticket = ticketRepository.findById(id).get();

       List<NoteModel> notes = ticket.getNotes();

       return notes;

    }

public TicketModel addNoteInTicket(Integer id, NoteModel note){
    TicketModel ticket=ticketRepository.findById(id).get();
    note.setDate(LocalDate.now());
    ticket.getNotes().add(note);
    note.setTicket(ticket);
    notesRepository.save(note);
    ticketRepository.save(ticket);
    return ticket;
}

}
