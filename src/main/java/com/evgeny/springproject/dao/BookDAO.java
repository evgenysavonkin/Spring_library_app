package com.evgeny.springproject.dao;

import com.evgeny.springproject.models.Book;
import com.evgeny.springproject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PersonDAO personDAO;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, PersonDAO personDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.personDAO = personDAO;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(nameofbook, author, yearofpublication) VALUES (?, ?, ?)", book.getNameOfBook(),
                book.getAuthor(), book.getYearOfPublication());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET nameofbook=?, author=?,yearofpublication=? WHERE book_id=?", book.getNameOfBook(),
                book.getAuthor(), book.getYearOfPublication(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void setPerson(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET human_id=? WHERE book_id=?", book.getHuman_id(), id);
    }

    public Person getPersonNameFromTable(int book_id) {
        Book book = jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{book_id}, new BookMapper()).
                stream().findAny().orElse(null);
        int human_id = book.getHuman_id();
        Person person = jdbcTemplate.query("SELECT * FROM Person where human_id=?", new Object[]{human_id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        return person;
    }

    public void resetPerson(int id) {
        jdbcTemplate.update("UPDATE Book SET human_id=? WHERE book_id=?", null, id);
    }

    public List<Book> getNamesOfBooks(int human_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE human_id=?", new Object[]{human_id},
                new BookMapper());
    }
}
