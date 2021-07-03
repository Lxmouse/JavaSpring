package dao;

import pojo.User;

import java.util.List;

public interface UserDao {
    public List<User> list();

    public long save(User user);

    public void saveUserAndRole(long userId, long[] roleIds);

    public void deleteUserAndRole(long userId);

    public void delete(long userId);

    public List<User> findPasswordByUsername(String username);
}
