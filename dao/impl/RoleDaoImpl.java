package dao.impl;

import dao.RoleDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Role;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Role> findRoleById(long id) {
        List<Role> roleList = template.query("select * from sys_user_role sur, sys_role sr where sur.userId = ? and sr.id = sur.roleId", new BeanPropertyRowMapper<>(Role.class),id);
        return roleList;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = template.query("select * from sys_role", new BeanPropertyRowMapper<>(Role.class));
        return roleList;
    }

    @Override
    public void delete(long roleId) {
        template.update("delete from sys_role where id = ?",roleId);
    }

    @Override
    public void deleteUserAndRole(long roleId) {
        template.update("delete from sys_user_role where roleId = ?",roleId);
    }

    @Override
    public void save(Role role) {
        template.update("insert into sys_role(roleName, roleDesc) value(?, ?)",role.getRoleName(),role.getRoleDesc());
    }
}
