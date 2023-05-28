package com.evgeny.springproject.util;

import com.evgeny.springproject.models.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if (!book.getNameOfBook().matches("^[a-zA-Zа-яА-я\\s]+$")){
            errors.rejectValue("nameOfBook", "", "Поле \"Название книги\" должно содержать только буквы и пробелы");
        }
        if (!book.getAuthor().matches("^[a-zA-Zа-яА-я\\s]+$")){
            errors.rejectValue("nameOfBook", "", "Поле \"Автор\" должно содержать только буквы и пробелы");
        }
    }

}
