package com.vnext.security.jwtex.infrastructure.dao;

import com.vnext.security.jwtex.models.User;
import com.vnext.security.jwtex.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Component
@AllArgsConstructor
public class UserDao implements UserRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Optional<User> findById(Long _id) {
        String query = "SELECT id, email, first_name, last_name, password FROM \"user\" WHERE id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", _id);

        List<User> result = this.jdbcTemplate.query(query, parameters, rowMapper());

        return addAuthorityToUniqueUser(result);
    }


    @Override
    public Optional<User> findByEmail(String _email) {
        String query = "SELECT id, email, first_name, last_name, password FROM \"user\" WHERE email = :email";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", _email);

        List<User> result = this.jdbcTemplate.query(query, parameters, rowMapper());
        return addAuthorityToUniqueUser(result);
    }


    @Override
    public User insert(User _user) {
        String query = "INSERT INTO \"user\" (email, first_name, last_name, password) VALUES (:email, :firstName, :lastName, :password) " +
            "RETURNING id, email, first_name, last_name, password";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", _user.getEmail());
        parameters.addValue("firstName", _user.getFirstName());
        parameters.addValue("lastName", _user.getLastName());
        parameters.addValue("password", _user.getPassword());

        List<User> result = this.jdbcTemplate.query(query, parameters, rowMapper());

        return result.get(0);
    }


    private Set<String> findAuthorities(Long _userId) {
        String query = "SELECT auth.authority FROM user_authority as uauth JOIN authority as auth ON uauth.authority_id = auth.id " +
            "WHERE uauth.user_id = :user_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", _userId);

        List<String> result = this.jdbcTemplate.query(query, parameters, (resultSet, rowNum) -> resultSet.getString("authority"));

        return new HashSet<>(result);
    }


    private Optional<User> addAuthorityToUniqueUser(List<User> _userList) {
        if (_userList.size() != 1) {
            return Optional.empty();
        }

        User user = _userList.get(0);
        user.setAuthorities(findAuthorities(user.getId()));

        return Optional.of(user);
    }


    private RowMapper<User> rowMapper()
    {
        return (ResultSet _resultSet, int _rowNum) ->
            new User(
                Long.parseLong(_resultSet.getString("id")),
                new HashSet<>(),
                _resultSet.getString("email"),
                _resultSet.getString("first_name"),
                _resultSet.getString("last_name"),
                _resultSet.getString("password"));
    }
}
