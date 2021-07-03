package service.impl;

import dao.RoleDao;
import dao.UserDao;
import pojo.Role;
import pojo.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.list();
        for(User user: userList){
            long id = user.getId();
            List<Role> roleList = roleDao.findRoleById(id);
            user.setRoleList(roleList);
        }
        return userList;
    }

    @Override
    public void save(User user, long[] roleIds) {
        long userId = userDao.save(user);
        userDao.saveUserAndRole(userId,roleIds);
    }

    @Override
    public void delete(long userId) {
        userDao.deleteUserAndRole(userId);
        userDao.delete(userId);

    }

    @Override
    public boolean login(String username, String password) {
        List<User> userList = userDao.findPasswordByUsername(username);
        if(userList == null || userList.size() == 0) return false;
        else {
            return password.equals(userList.get(0).getPassword());
        }
    }
}
