package ru.gusev.springcourse.dao;

import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gusev.springcourse.models.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CityDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CityDao (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    public List <City> index () {
        return jdbcTemplate.query("SELECT * FROM City", new BeanPropertyRowMapper<>(City.class));
    }

    public City show (int id) {
    return jdbcTemplate.query("SELECT * FROM City WHERE id =?", new Object[]{id}, new BeanPropertyRowMapper<>(City.class))
            .stream().findAny().orElse(null);

    }

    public void save (City city) {

    jdbcTemplate.update("INSERT INTO City VALUES (DEFAULT,?,?,?)",city.getName(), city.getPopulation(), city.getSquare());

            }

    public void update (int id, City updateCity) {
        System.out.println(updateCity.getName());
       jdbcTemplate.update("UPDATE City SET name=?, population=?, square=? WHERE id=?",updateCity.getName(),
               updateCity.getPopulation(),updateCity.getSquare(), id);

    }

    public void delete (int id) {
     jdbcTemplate.update( "DELETE FROM City WHERE id=?", id);

    }
}
