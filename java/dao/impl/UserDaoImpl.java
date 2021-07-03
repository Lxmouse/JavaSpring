package dao.impl;

import dao.UserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template;


    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<User> list() {
        List<User> userList = template.query("select * from sys_user", new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    @Override
    public long save(final User user) {
        final String sql = "insert into sys_user(username, email, password, phoneNum) value(?,?,?,?)";
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, user.getUsername());
                preparedStatement.setObject(2, user.getEmail());
                preparedStatement.setObject(3, user.getPassword());
                preparedStatement.setObject(4, user.getPhoneNum());
                return preparedStatement;
            }
        };

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(preparedStatementCreator, keyHolder);
        long userId = keyHolder.getKey().longValue();
        return userId;
    }

    @Override
    public void saveUserAndRole(long userId, long[] roleIds) {
        for(long roleId:roleIds){
            template.update("insert into sys_user_role(userId, roleId) value(?,?)",userId,roleId);
        }
    }

    @Override
    public void deleteUserAndRole(long userId) {
        template.update("delete from sys_user_role where userId = ?",userId);
    }

    @Override
    public void delete(long userId) {
        template.update("delete from sys_user where id = ?",userId);
    }

    @Override
    public List<User> findPasswordByUsername(String username) {
        List<User> userList = template.query("select * from sys_user where username = ?", new BeanPropertyRowMapper<User>(User.class),username);
        return userList;
    }

}
