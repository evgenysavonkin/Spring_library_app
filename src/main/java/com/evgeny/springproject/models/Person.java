package com.evgeny.springproject.models;

import javax.validation.constraints.*;

public class Person {
    private int human_id;
    @NotEmpty(message = "Поле \"ФИО\" не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле \"ФИО\" должно быть между 2 и 100 символами")
    @Pattern(regexp = "^[a-zA-Zа-яА-я\\s]+$", message = "Поле \"ФИО\" должно содержать только буквы и пробелы")
    private String fullName;
    @Max(value = 2023)
    @Min(value = 1923)
    private int yearOfBirth;

    public Person(int human_id, String fullName, int yearOfBirth) {
        this.human_id = human_id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {

    }

    public int getHuman_id() {
        return human_id;
    }

    public void setHuman_id(int human_id) {
        this.human_id = human_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
