package com.evgeny.springproject.controllers;

import com.evgeny.springproject.dao.BookDAO;
import com.evgeny.springproject.dao.PersonDAO;
import com.evgeny.springproject.models.Book;
import com.evgeny.springproject.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(PersonDAO personDAO, BookDAO bookDAO, BookValidator bookValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "libraryBook/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){
        return "libraryBook/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()){
            return "libraryBook/new";
        }
        bookDAO.save(book);
        return "redirect:/book";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "libraryBook/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "libraryBook/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("personList", personDAO.index());
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("personWithName", bookDAO.getPersonNameFromTable(id));
        return "libraryBook/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("{id}/update")
    public String setPerson(@ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "libraryBook/show";
        }
        bookDAO.setPerson(book, id);
        return "redirect:/book";
    }

    @PatchMapping("{id}/reset")
    public String resetBook(@PathVariable("id") int id){
        bookDAO.resetPerson(id);
        return "redirect:/book";
    }
}
