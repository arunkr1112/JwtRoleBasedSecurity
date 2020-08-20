package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_PERMISSION", joinColumns = {
            @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "permissionId") })
    private List<UserPermission> userPermissions;

    public UserRole() {
    }

    public UserRole(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserPermission> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(List<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }
}
