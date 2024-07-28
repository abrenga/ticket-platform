package it.tickets.manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tickets.manager.Model.NoteModel;


public interface NotesRepository extends JpaRepository<NoteModel,Integer>{

}
