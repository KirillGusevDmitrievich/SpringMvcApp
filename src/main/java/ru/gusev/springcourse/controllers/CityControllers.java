package ru.gusev.springcourse.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gusev.springcourse.dao.CityDao;
import ru.gusev.springcourse.models.City;

import java.sql.SQLException;

@Controller
@RequestMapping("/city")
public class CityControllers {
    private CityDao cityDao;

   public CityControllers (CityDao cityDao) {

       this.cityDao=cityDao;
   }

    @GetMapping()
    public String index (Model model) throws SQLException {
        model.addAttribute("city", cityDao.index());
        return "city/index";
   }


    @GetMapping ("/{id}")
    public String  show (@PathVariable ("id") int id, Model model) {
        model.addAttribute("cityId", cityDao.show(id));
        return "city/show";
        }

    @GetMapping("/new")
    public String newCity (Model model) {
       model.addAttribute("city", new City());
       return "city/new";}


    @PostMapping()
    public String create (@ModelAttribute ("city") @Valid City city,
                          BindingResult bindingResult) {
       if(bindingResult.hasErrors())
           return "city/new";
    cityDao.save(city);

    return "redirect:/city";}


    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") int id) {
    model.addAttribute("city", cityDao.show(id));
    return "city/edit";
    }

    @PatchMapping ("/{id}")
    public String update (@ModelAttribute("city") @Valid City city, BindingResult bindingResult, @PathVariable ("id") int id) {
       if (bindingResult.hasErrors())
           return "city/edit";
       cityDao.update (id,city);
       return "redirect:/city";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable ("id") int id){
       cityDao.delete (id);
       return "redirect:/city";

    }

}
