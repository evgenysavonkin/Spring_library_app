package com.evgeny.springproject.util;

import com.evgeny.springproject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (!person.getFullName().matches("[a-zA-Zа-яА-я\\s]+$")){
            errors.rejectValue("fullName", "", "Поле \"ФИО\" должно содержать только буквы и пробелы");
        }
        if (person.getYearOfBirth() <= 1923 || person.getYearOfBirth() >= 2023){
            errors.rejectValue("yearOfBirth", "", "Год рождения должен быть между 1923 и 2023 годами");
        }
    }
}
