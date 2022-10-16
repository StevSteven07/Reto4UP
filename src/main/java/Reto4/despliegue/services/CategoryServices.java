package Reto4.despliegue.services;


import Reto4.despliegue.entitys.Category;
import Reto4.despliegue.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }

    public Category save (Category category){
        if (validarCampos(category)) {
            if (category.getId() == null) {
                return categoryRepository.save(category);
            } else {
                Optional<Category> e = categoryRepository.getCategory(category.getId());
                if (e.isPresent()) {
                    return category;
                } else {
                    return categoryRepository.save(category);
                }
            }
        } return category;
    }

    public Category update(Category category){
         if(validarCampos(category)) {
             if (category.getId() != null) {
                 Optional<Category> c = categoryRepository.getCategory(category.getId());
                 if (c.isPresent()) {
                     if (category.getName() != null) {
                         c.get().setName(category.getName());
                     }
                     if (category.getDescription() != null) {
                         c.get().setDescription(category.getDescription());
                     }
                     if (category.getClouds() != null) {
                         c.get().setClouds(category.getClouds());
                     }
                     categoryRepository.save(c.get());
                     return c.get();
                 } else {
                     return category;
                 }
             } else {
                 return category;
             }
         } return category;
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Category> product = categoryRepository.getCategory(id);
        if (product.isPresent()){
            categoryRepository.delete(product.get());
            flag=true;
        }
        return flag;
        
        /* otra manera 
        Boolean d= getCategory(id).map(category -> { categoryRepository.delete(category); 
        return true; })
        */
    }

    public boolean validarCampos (Category category){
        return (category.getName().length()<=45 && category.getDescription().length()<=250);
    }
}
