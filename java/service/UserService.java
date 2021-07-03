package service;

import pojo.User;

import java.util.List;

public interface UserService {

    public List<User> list();

    public void save(User user, long[] roleIds);

    public void delete(long userId);

    public boolean login(String username, String password);
}
