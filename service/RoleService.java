package service;

import pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> list();

    public void delete(long roleId);

    public void save(Role role);
}
