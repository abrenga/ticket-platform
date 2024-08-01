package it.tickets.manager.Repository;

import it.tickets.manager.Model.CategoriesModel;
import it.tickets.manager.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import it.tickets.manager.Model.TicketModel;
import it.tickets.manager.Model.TicketState;

import java.util.List;


public interface TicketsRepository extends JpaRepository<TicketModel,Integer>{
List<TicketModel> findByUser(UserModel user);

List<TicketModel> findByTitleLike(String title);


List<TicketModel> findByCategory(CategoriesModel categoies);

List<TicketModel> findByState(TicketState state);


    List<TicketModel> findByTitleLikeAndUser(String s, UserModel user);
}