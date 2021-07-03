package dao;

import pojo.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findRoleById(long id);

    public List<Role> list();

    public void delete(long roleId);

    public void deleteUserAndRole(long roleId);

    public void save(Role role);
}
