package it.tickets.manager.Service;

import it.tickets.manager.Model.CategoriesModel;

import java.util.List;

public interface ICategoryService {
    List<CategoriesModel> showCAtegory();
    CategoriesModel findCategory(Integer id);
}
