package service.impl;

import dao.RoleDao;
import pojo.Role;
import service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = roleDao.list();
        return roleList;
    }

    @Override
    public void delete(long roleId) {
        roleDao.deleteUserAndRole(roleId);
        roleDao.delete(roleId);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
