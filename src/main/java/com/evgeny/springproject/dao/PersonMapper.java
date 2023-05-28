package com.evgeny.springproject.dao;

import com.evgeny.springproject.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setHuman_id(resultSet.getInt("human_id"));
        person.setFullName(resultSet.getString("fullname"));
        person.setYearOfBirth(resultSet.getInt("yearofbirth"));
        return person;
    }
}
