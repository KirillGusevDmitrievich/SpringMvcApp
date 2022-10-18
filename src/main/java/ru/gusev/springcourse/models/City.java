package ru.gusev.springcourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class City {
    private int id;
    @NotEmpty (message = "Имя не должно быть пустым!")
    @Size(min=2, max =30, message = "Имя должно быть в диапазоне от 2 до 30 символов")
    private String name;
    @Min(value=0, message = "Население не может быть меньше 0")
    private int population;
    @Min(value = 0, message = "Площадь не может быть меньше 0")
    private int square;

    public City () {}
    public City(int id, String name, int population, int square) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.square=square;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
