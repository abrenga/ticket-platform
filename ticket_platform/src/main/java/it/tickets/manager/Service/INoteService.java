package it.tickets.manager.Service;

import it.tickets.manager.Model.NoteModel;
import it.tickets.manager.Model.TicketModel;

import java.util.List;


public interface INoteService {
     List<NoteModel> showAllNotesByTicketId(Integer id);
     TicketModel addNoteInTicket(Integer id, NoteModel note);
}
