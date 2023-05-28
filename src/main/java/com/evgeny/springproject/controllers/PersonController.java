package com.evgeny.springproject.controllers;

import com.evgeny.springproject.dao.BookDAO;
import com.evgeny.springproject.dao.PersonBookDAO;
import com.evgeny.springproject.dao.PersonDAO;
import com.evgeny.springproject.models.Person;
import com.evgeny.springproject.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonBookDAO personBookDAO;
    private final PersonValidator personValidator;
    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO, PersonBookDAO personBookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personBookDAO = personBookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "libraryPerson/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "libraryPerson/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "libraryPerson/new";
        }
        personDAO.save(person);
        return "redirect:/person";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "libraryPerson/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,@PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "libraryPerson/edit";
        }
        personDAO.update(id, person);
        return "redirect:/person";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", personBookDAO.showBooksBoolValue(id));
        model.addAttribute("bookList", bookDAO.getNamesOfBooks(id));
        model.addAttribute("person", personDAO.show(id));
        return "libraryPerson/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/person";
    }
}
