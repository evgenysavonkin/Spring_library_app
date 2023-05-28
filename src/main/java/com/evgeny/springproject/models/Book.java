package com.evgeny.springproject.models;

import javax.validation.constraints.*;
import java.util.Calendar;

public class Book {
    private int book_id;
    private int human_id;
    @NotEmpty(message = "Поле \"Название книги\" не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле \"Название книги\" должно быть между 2 и 100 символами")
    @Pattern(regexp = "^[a-zA-Zа-яА-я\\s]+$", message = "Поле \"Название книги\" должно содержать только буквы и пробелы")
    private String nameOfBook;
    @NotEmpty(message = "Поле \"Автор\" не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле \"Автор\" должно быть между 2 и 100 символами")
    @Pattern(regexp = "^[a-zA-Zа-яА-я\\s]+$", message = "Поле \"Автор\" должно содержать только буквы и пробелы")
    private String author;
    @Min(value = 1000, message = "Минимальный год публикации равен 1000")
    @Max(value = 2023, message = "Максимальный год публикации равен 2023")
    private int yearOfPublication; //Год издания

    public Book(int book_id, int human_id, String nameOfBook, String author, int yearOfPublication) {
        this.book_id = book_id;
        this.human_id = human_id;
        this.nameOfBook = nameOfBook;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getHuman_id() {
        return human_id;
    }

    public void setHuman_id(int human_id) {
        this.human_id = human_id;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
