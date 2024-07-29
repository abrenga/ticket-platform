package it.tickets.manager.Repository;

import it.tickets.manager.Model.CategoriesModel;
import it.tickets.manager.Model.NoteModel;
import it.tickets.manager.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.TicketState;

import java.util.List;
import java.util.Optional;


public interface TicketsRepository extends JpaRepository<TicketModel,Integer>{
List<TicketModel> findByUser(UserModel user);

List<TicketModel> findByTitleLike(String title);


List<TicketModel> findByCategoriesModel(CategoriesModel categoies);

List<TicketModel> findByState(TicketState state);
}