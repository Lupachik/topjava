package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
            insertRole(user);
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, " +
                            "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", parameterSource) == 0) {
                return null;
            }
            deleteRole(user);
            insertRole(user);
        }
        return setRolesFromDb(user);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return setRolesFromDb(DataAccessUtils.singleResult(users));
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        return setRolesFromDb(DataAccessUtils.singleResult(users));
    }

    @Override
    public List<User> getAll() {
//        http://bazhenov.me/blog/2014/07/20/java-8-top10.html
//        https://www.tutorialspoint.com/java/util/enumset_noneof.htm
        Map<Integer, Set<Role>> mapRoles = new HashMap<>(); // get map where key -  user_id, value set roles
        jdbcTemplate.query("SELECT * FROM user_roles", rs -> {
            mapRoles.computeIfAbsent(rs.getInt("user_id"), u -> EnumSet.noneOf(Role.class))
                    .add(Role.valueOf(rs.getString("role")));
        });

        List<User> userList = jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);
        userList.forEach(user -> user.setRoles(mapRoles.get(user.getId())));
        return userList;
    }

    // get list user roles from db
    private User setRolesFromDb(User user) {
        if (user != null) {
            List<Role> roles = jdbcTemplate.query("SELECT role FROM user_roles WHERE user_id=?",
                    (rs, rowNum) -> Role.valueOf(rs.getString("role")), user.getId());
            user.setRoles(roles);
        }
        return user;
    }

    // methods for insert/delete role

    private void deleteRole(User user) {
        jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", user.getId());
    }


    //    https://mkyong.com/spring/spring-jdbctemplate-batchupdate-example/
    private void insertRole(User user) {
        Set<Role> roleSet = user.getRoles();
        List<Role> roleList = new ArrayList<>(roleSet);

        if (roleSet.size() != 0) {
            jdbcTemplate.batchUpdate("INSERT INTO user_roles (user_id, role) VALUES (?, ?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, user.getId());
                    ps.setString(2, roleList.get(i).toString());
                }

                @Override
                public int getBatchSize() {
                    return roleSet.size();
                }
            });
        }
    }

}
