package com.evgeny.springproject.dao;

import com.evgeny.springproject.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBook_id(resultSet.getInt("book_id"));
        book.setNameOfBook(resultSet.getString("nameofbook"));
        book.setAuthor(resultSet.getString("author"));
        book.setYearOfPublication(resultSet.getInt("yearofpublication"));
        book.setHuman_id(resultSet.getInt("human_id"));
        return book;
    }
}
