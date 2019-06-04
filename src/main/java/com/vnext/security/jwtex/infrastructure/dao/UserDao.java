package com.vnext.security.jwtex.infrastructure.dao;

import com.vnext.security.jwtex.models.User;
import com.vnext.security.jwtex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Component
public class UserDao implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User insert(User _user) {
        String query = "INSERT INTO user(email, first_name, last_name, password) VALUES (:email, :firstName, :lastName, :password)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", _user.getEmail());
        parameters.addValue("firstName", _user.getFirstName());
        parameters.addValue("lastName", _user.getLastName());
        parameters.addValue("password", _user.getPassword());

        List<User> result = this.jdbcTemplate.query(query, parameters, rowMapper());

        return result.get(0);
    }


    @Override
    public Optional<User> findByEmail(String _email) {
        String query = "SELECT id, email, first_name, last_name, password FROM user WHERE email = :email";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", _email);

        List<User> result = this.jdbcTemplate.query(query, parameters, rowMapper());
        if (result.size() != 1) {
            return Optional.empty();
        }

        return Optional.ofNullable(result.get(0));
    }


    private RowMapper<User> rowMapper()
    {
        return (ResultSet _resultSet, int _rowNum) ->
        {
            return new User(
                Long.parseLong(_resultSet.getString("id")),
                new HashSet<>(),
                _resultSet.getString("email"),
                _resultSet.getString("first_name"),
                _resultSet.getString("last_name"),
                _resultSet.getString("password"));
        };
    }
}
