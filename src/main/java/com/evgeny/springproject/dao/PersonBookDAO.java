package com.evgeny.springproject.dao;

import com.evgeny.springproject.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonBookDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PersonBookDAO(JdbcTemplate jdbcTemplate, PersonDAO personDAO, BookDAO bookDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    public Book showBooksBoolValue(int human_id) {
        Book book = null;
        List<Book> bookList = bookDAO.index();
        for (Book b : bookList) {
            if (b.getHuman_id() == human_id) {
                book = b;
            }
        }
        return book;
    }
}
