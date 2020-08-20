package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class UserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int permissionId;

    private String permissionName;

    public UserPermission() {
    }

    public UserPermission(int permissionId, String permissionName) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
