package it.tickets.manager.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tickets.manager.Model.UserModel;


public interface UserRepository extends JpaRepository<UserModel,Integer>{
   Optional<UserModel> findByUsername(String username);

}