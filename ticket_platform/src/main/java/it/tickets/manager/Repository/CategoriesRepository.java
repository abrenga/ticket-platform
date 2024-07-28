package it.tickets.manager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tickets.manager.Model.CategoriesModel;

public interface CategoriesRepository extends JpaRepository <CategoriesModel, Integer>{
}