package it.tickets.manager.Service;

import it.tickets.manager.Model.CategoriesModel;
import it.tickets.manager.Repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryService implements ICategoryService{

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<CategoriesModel> showCAtegory(){
       List<CategoriesModel> categories= categoriesRepository.findAll();
       return  categories;
    }

    public CategoriesModel findCategory(Integer id){
        CategoriesModel category = categoriesRepository.findById(id).get();
        return category;
    }
}
